import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;

public class BuildingDialog extends Dialog {
    public Button dirButton;

    public BuildingDialog(int f, int e, int r, int n, Room R, int c) {

        setTitle("Building Overview");
        setHeaderText(null);
        ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        getDialogPane().getButtonTypes().addAll(okButton);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));

        Label l1 = new Label("Num Floors:");
        Label l2 = new Label("Num Exits:");
        Label l3 = new Label("Num Rooms");
        Label l4 = new Label("Total Size:");

        TextField floorField = new TextField(String.valueOf(f));
        floorField.setEditable(false);
        floorField.setMaxWidth(70);

        TextField exitField = new TextField(String.valueOf(e));
        exitField.setEditable(false);
        exitField.setMaxWidth(70);


        TextField roomField = new TextField(String.valueOf(r));
        roomField.setEditable(false);
        roomField.setMaxWidth(70);

        TextField sizeField = new TextField(String.valueOf(n) + " Sq.Ft");
        sizeField.setEditable(false);
        sizeField.setMaxWidth(70);


        dirButton = new Button("Directory Listing");
        dirButton.setMinWidth(145);

        grid.add(l1,0,0);
        grid.add(floorField, 1, 0);
        grid.add(l2, 0, 1);
        grid.add(exitField, 1,1);
        grid.add(l3, 0, 2);
        grid.add(roomField, 1, 2);
        grid.add(l4, 0, 3);
        grid.add(sizeField, 1, 3);
        grid.add(dirButton,0,4,2,1);


        getDialogPane().setContent(grid); // Puts the stuff on the window

        dirButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                DirectoryDialog dialog = new DirectoryDialog(R,r,c);
                dialog.showAndWait();
            }});




    }
}
