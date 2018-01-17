import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;


public class RoomInfoDialog extends Dialog {
    private Building model;
    private FloorBuilderView view;

    public RoomInfoDialog(int currentFloor, int currentColor, Room r) {
        model = Building.example();

        setTitle("Room Details");
        setHeaderText(null);
        ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        getDialogPane().getButtonTypes().addAll(okButton, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));

        Label l1 = new Label("Occupant:");
        Label l2 = new Label("Position:");
        Label l3 = new Label("Number:");
        Label l4 = new Label("Floor:");
        Label l5 = new Label("Size:");

        TextField occupantField = new TextField(r.getOccupant());
        occupantField.setPromptText("Person who occupies this room");
        occupantField.setMinWidth(300);

        TextField positionField = new TextField(r.getPosition());
        positionField.setPromptText("Job position/title of this person");
        positionField.setMinWidth(300);

        TextField numberField = new TextField(r.getNumber());
        numberField.setPromptText("The room number");
        numberField.setMinWidth(140);

        TextField floorField = new TextField();
        floorField.setText(model.getFloorPlan(currentFloor).getName());
        floorField.setEditable(false);
        floorField.setMinWidth(300);

        TextField sizeField = new TextField();
        sizeField.setText("1 Sq.Ft.");
        sizeField.setEditable(false);
        sizeField.setMinWidth(300);

        Button colorButton = new Button();
        colorButton.setStyle("-fx-base: " + view.ROOM_COLORS[currentColor]);
        colorButton.setFocusTraversable(false);
        colorButton.setMinWidth(140);

        grid.add(l1,0,0);
        grid.add(occupantField, 1, 0,2,1);
        grid.add(l2, 0, 1);
        grid.add(positionField, 1,1,2,1);
        grid.add(l3, 0, 2);
        grid.add(numberField, 1, 2);
        grid.add(colorButton,2,2);
        grid.add(l4, 0, 3);
        grid.add(floorField, 1, 3,2,1);
        grid.add(l5, 0, 4);
        grid.add(sizeField, 1, 4,2,1);


        getDialogPane().setContent(grid); // Puts the stuff on the window

        //Node okB = getDialogPane().lookupButton(okButton);
        setResultConverter(new Callback<ButtonType, Room>() {
            public Room call(ButtonType b) {
                if (b == okButton) {
                    r.setOccupant(occupantField.getText());
                    r.setPosition(positionField.getText());
                    r.setNumber(numberField.getText());
                    return r;
                }
                return null;
            }
        });

    }
}