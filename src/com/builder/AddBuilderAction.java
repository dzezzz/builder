package com.builder;

import java.net.MalformedURLException;

import com.builder.services.ReadClassService;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.command.CommandProcessor;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;

/**
 * @author marcinjaz
 */
public class AddBuilderAction extends AnAction {

	private ReadClassService readClassService = new ReadClassService();

	@Override
	public void actionPerformed(AnActionEvent e) {
		Project project = e.getProject();
		if (project == null) {
			return;
		}
		VirtualFile vFile = e.getData(PlatformDataKeys.VIRTUAL_FILE);


		String path = vFile.getParent().getPath();
		String className = vFile.getNameWithoutExtension();
		Document document = e.getData(LangDataKeys.EDITOR).getDocument();
		String fileContent = document.getText();
		Class<?> clazz;
		try {
			clazz = readClassService.loadClass(fileContent, className);
		} catch (Exception e1) {
			//TODO Mj exception handling
			System.err.println("Errors loading class");
		}


		String contentWithBuilder = fileContent + " magic";

		writeToFile(project, document, contentWithBuilder);

		System.out.println("action performed");
	}

	private void writeToFile(Project project, Document document, String contentWithBuilder) {
		final Runnable readRunner = () -> document.setText(contentWithBuilder);
		ApplicationManager.getApplication()
				.invokeLater(() -> CommandProcessor.getInstance().executeCommand(project,
						() -> ApplicationManager.getApplication().runWriteAction(readRunner), "DiskRead", null));
	}
}
