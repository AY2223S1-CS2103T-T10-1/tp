package seedu.address.ui;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.model.module.Module;
import seedu.address.model.module.task.Task;

/**
 * A UI component that displays information of a {@code Module}.
 */
public class ModuleCard extends UiPart<Region> {

    private static final Desktop desktop = Desktop.getDesktop();
    private static final String FXML = "ModuleListCard.fxml";
    private static final String MESSAGE_LINK_LAUNCH_FAILURE = "Error: Link cannot be launched by your desktop";
    private static final String LINK_HEADER_PLAIN_TEXT = "http";
    private static final String LINK_HEADER_TEXT_WITH_SLASH = "https://";
    private static final String LINK_TEXT_COLOR = "-fx-text-fill: #FFCC66"; //Light Yellow


    /**
     * Note: Certain keywords such as "location" and "resources" are reserved
     * keywords in JavaFX. As a consequence, UI elements' variable names
     * cannot be set to such keywords an exception will be thrown by JavaFX
     * during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Module module;

    @FXML
    private HBox cardPane;
    @FXML
    private Label moduleCode;
    @FXML
    private Label id;
    @FXML
    private Label moduleTitle;
    private TaskListPanel taskListPanel;
    @FXML
    private StackPane taskListPanelPlaceholder;
    @FXML
    private FlowPane links;

    /**
     * Creates a {@code ModuleCode} with the given {@code Module} and index to
     * display.
     */
    public ModuleCard(Module module, int displayedIndex) {
        super(FXML);
        this.module = module;
        id.setText(displayedIndex + ". ");
        moduleCode.setText(module.getModuleCodeAsUpperCaseString());
        moduleTitle.setText(module.getModuleTitleAsUpperCaseString());
        module.getLinks().stream()
                .forEach(link -> links.getChildren()
                        .add(createHyperLinkNode(link.linkName)));
        ObservableList<Task> taskList = module.getTasks();
        taskListPanel = new TaskListPanel(taskList);
        taskListPanelPlaceholder.getChildren().add(taskListPanel.getRoot());
    }

    private static Hyperlink createHyperLinkNode(String linkUrl) {
        Hyperlink node = new Hyperlink(linkUrl);
        node.setStyle(LINK_TEXT_COLOR);
        if (!linkUrl.substring(0, 4).equals(LINK_HEADER_PLAIN_TEXT)) {
            linkUrl = LINK_HEADER_TEXT_WITH_SLASH + linkUrl;
        }
        final String finalLinkUrl = linkUrl;
        node.setOnAction(e -> {
            try {
                desktop.browse(URI.create(finalLinkUrl));
            } catch (IOException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(MESSAGE_LINK_LAUNCH_FAILURE);
                alert.showAndWait();
            }
        });
        return node;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModuleCard)) {
            return false;
        }

        // state check
        ModuleCard card = (ModuleCard) other;
        return id.getText().equals(card.id.getText())
                && module.equals(card.module);
    }
}
