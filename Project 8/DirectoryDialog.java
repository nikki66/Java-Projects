import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.util.Optional;

public class DirectoryDialog extends Dialog {
    public DirectoryDialog(Room r ,int numR, int currentfloor) {

        setTitle("Directory Listing");
        setHeaderText(null);
        ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        getDialogPane().getButtonTypes().addAll(okButton);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));

        ListView<String> roomList = new ListView<String>();

        if(numR > 0){
            roomList.setItems(FXCollections.observableArrayList(r.getNumber() + " - " + r.getOccupant() + " (" + r.getPosition() + ") " ));
        }

        roomList.setMinWidth(300);
        roomList.setMaxHeight(200);


        Button sButton = new Button("Search");
        sButton.setMinWidth(300);

        grid.add(roomList, 0, 0);
        grid.add(sButton, 0, 1);



        getDialogPane().setContent(grid); // Puts the stuff on the window

        sButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("Input Required");
                dialog.setHeaderText(null);
                dialog.setContentText("Please enter the full name of the person that you are searching for:");
                Optional<String> result = dialog.showAndWait();
                if (result.isPresent()){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Search Results");
                    alert.setHeaderText(null);

                    if(numR > 0 && result.get().equals(r.getOccupant())){
                        alert.setContentText(result.get() + " is our " + r.getPosition() + " and can be located on the "
                                + Building.example().getFloorPlan(currentfloor).getName() + " in room " + r.getNumber());
                        //alert.showAndWait();

                    }else{
                        alert.setContentText("That name does not match anyone in our records, please try again.");
                    }

                    alert.showAndWait();
                }


            }});
    }
}