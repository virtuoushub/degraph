package de.schauderhaft.degraph.gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import de.schauderhaft.degraph.gui.util.FXMLUtil;
import de.schauderhaft.degraph.model.Node;

/**
 * Controller for one node visualization.
 */
public class NodeController extends AnchorPane {

	private static final double SPACE_RATIO = 1.4;
	public static final String LABEL_NAME = "#nodeNameLabel";
	public static final String PANE_NAME = "#nodeView";
	public static final double WIDTH = 130.0;
	public static final double HEIGHT = 79.0;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	private final Node node;

	private final NodeLabelConverter converter = new NodeLabelConverter();

	@FXML
	void initialize() {
		initalizeLabels();
	}

	private void initalizeLabels() {
		Label label = (Label) this.lookup(LABEL_NAME);
		label.setText(converter.getNodeName(node));
	}

	public NodeController(Node node) {
		this.node = node;
		FXMLUtil.loadAndSetController(this, "NodeView.fxml");

	}

	@FXML
	void onMouseClicked(MouseEvent event) {
	}

	public void setLayout(int x, int y) {
		this.setLayoutX(x);
		this.setLayoutY(y);

	}

	public void setLayout(NodePosition position) {
		setLayout(position.x, position.y);
	}

	/**
	 * Create pane size depending on children(content) size.
	 */
	public void fitToSize() {
		Pane nodeContentView = (Pane) this.lookup(PANE_NAME);

		Label nodeName = (Label) this.lookup(LABEL_NAME);
		int size = nodeContentView.getChildrenUnmodifiable().size();
		if (size > 0) {

			double prefWidth = size * SPACE_RATIO * WIDTH;
			nodeContentView.setPrefWidth(prefWidth);
			nodeContentView.setPrefHeight(HEIGHT * SPACE_RATIO);
			nodeName.setPrefWidth(size * SPACE_RATIO); // label doesnt work

		} else {
			// no kids
			nodeContentView.setPrefWidth(WIDTH);
			nodeName.setPrefWidth(WIDTH);

		}

	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("this.size: " + this.getPrefHeight() + " x "
				+ this.getPrefWidth() + " Position (" + this.getLayoutX()
				+ " | " + this.getLayoutY() + ")\n");
		Label Label = (Label) this.lookup(LABEL_NAME);
		sb.append("Label: " + Label.getText() + " size: "
				+ Label.getPrefHeight() + " x " + Label.getPrefWidth()
				+ " x | y" + Label.getLayoutX() + " | " + Label.getLayoutY()
				+ "\n");
		Pane pane = (Pane) this.lookup(PANE_NAME);
		sb.append("Pane.size: " + pane.getPrefHeight() + " x "
				+ pane.getPrefWidth() + " x | y" + pane.getLayoutX() + " | "
				+ pane.getLayoutY());

		return sb.toString();
	}
}
