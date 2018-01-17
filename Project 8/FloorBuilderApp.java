import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class FloorBuilderApp extends Application  {
    private FloorBuilderView   view;
    private Building           model;
    private int                currentFloor;    // Index of the floor being displayed
    private int                currentColor;    // Index of the current room color
    private Room R;

    private MenuItem      floorM, floor2, floor3, floor4, floorB;

    public void start(Stage primaryStage) {
        model = Building.example();
        currentFloor = 0;
        currentColor = 0;

        VBox paneT = new VBox(); //added
        VBox aPane = new VBox();
        view = new FloorBuilderView(model);
        view.setPrefWidth(Integer.MAX_VALUE);
        view.setPrefHeight(Integer.MAX_VALUE);


        // Create the File menu
        Menu fileMenu = new Menu("_SelectFloor");
        floorM = new MenuItem(FloorPlan.floor1().getName());
        floor2 = new MenuItem(FloorPlan.floor2().getName());
        floor3 = new MenuItem(FloorPlan.floor3().getName());
        floor4 = new MenuItem(FloorPlan.floor4().getName());
        floorB = new MenuItem(FloorPlan.floor5().getName());

        fileMenu.getItems().addAll(floor4,floor3, floor2, floorM, new SeparatorMenuItem(), floorB);

        // Add the menus to a menubar and then add the menubar to the pane
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu);
        paneT.getChildren().addAll(menuBar);

        aPane.getChildren().addAll(paneT,view);
        primaryStage.setTitle("Floor Plan Builder");
        primaryStage.setScene(new Scene(aPane, 370,340)); //modified
        primaryStage.show();

        //menubuttons event handlers
        floorM.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                currentFloor = 0;
                view.update(currentFloor,currentColor);
            }
        });

        floor2.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                currentFloor = 1;
                view.update(currentFloor,currentColor);
            }
        });

        floor3.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                currentFloor = 2;
                view.update(currentFloor,currentColor);
            }
        });

        floor4.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                currentFloor = 3;
                view.update(currentFloor,currentColor);
            }
        });

        floorB.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                currentFloor = 4;
                view.update(currentFloor,currentColor);
            }
        });




        // Plug in the floor panel event handlers:
        for (int r=0; r<model.getFloorPlan(0).size(); r++) {
            for (int c=0; c<model.getFloorPlan(0).size(); c++) {
                view.getFloorTileButton(r, c).setOnAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent event) {
                        handleTileSelection(event);
                    }});
            }
        }

        // Plug in the radioButton event handlers
        view.getEditWallsButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                view.update(currentFloor, currentColor);
            }});
        view.getSelectExitsButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                view.update(currentFloor, currentColor);
            }});
        view.getEditRoomsButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                view.update(currentFloor, currentColor);
            }});
        view.getDefineRoomsButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                view.update(currentFloor, currentColor);
            }});

        // Plug in the office color button
        view.getRoomColorButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                currentColor = (currentColor + 1)%view.ROOM_COLORS.length;
                view.update(currentFloor, currentColor);
            }});


        view.getBuildingOverviewButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                int roomNum =0;
                int nonwall =(20*20*4);
                for(int i=0; i<model.getFloorPlans().length;i++){
                    roomNum += model.getFloorPlans()[i].getNumberOfRooms();
                    for (int r=0; r<model.getFloorPlan(0).size(); r++) {
                        for (int c=0; c<model.getFloorPlan(0).size(); c++) {
                            if(model.getFloorPlans()[i].wallAt(r,c)){
                                nonwall -= 1;
                            }

                        }
                    }

                }
                Dialog dialog = new BuildingDialog(model.getNumFloors(),model.getNumExits(),roomNum,nonwall,R, currentFloor);
                dialog.showAndWait();
                view.update(currentFloor, currentColor);
            }});
        view.update(currentFloor, currentColor);


    }

    // Handle a Floor Tile Selection
    private void handleTileSelection(ActionEvent e) {
        // Determine which row and column was selected
        int r=0, c=0;
        OUTER:
        for (r=0; r<model.getFloorPlan(0).size(); r++) {
            for (c=0; c<model.getFloorPlan(0).size(); c++) {
                if (e.getSource() == view.getFloorTileButton(r, c))
                    break OUTER;
            }
        }

        // Check if we are in edit wall mode, then toggle the wall
        if (view.getEditWallsButton().isSelected()) {
            model.getFloorPlan(currentFloor).setWallAt(r, c, !model.getFloorPlan(currentFloor).wallAt(r, c));
            // Remove this tile from the room if it is on one, because it is now a wall
            Room room = model.getFloorPlan(currentFloor).roomAt(r, c);
            if (room != null)
                room.removeTile(r, c);
        }

        // Otherwise check if we are in edit exits mode
        else if (view.getSelectExitsButton().isSelected()) {
            if (model.exitAt(r, c) != null)
                model.removeExit(r, c);
            else {
                model.addExit(r, c);
                // Remove this tile from the room if it is on one, because it is now an exit
                Room off = model.getFloorPlan(currentFloor).roomAt(r, c);
                if (off != null)
                    off.removeTile(r, c);
            }
        }

        // Otherwise check if we are selecting a room tile
        else if (view.getEditRoomsButton().isSelected()) {
            if (!model.getFloorPlan(currentFloor).wallAt(r, c) && !model.hasExitAt(r, c)) {
                Room room = model.getFloorPlan(currentFloor).roomAt(r, c);
                if (room != null) {
                    room.removeTile(r, c);
                    if (room.getNumberOfTiles() == 0)
                        model.getFloorPlan(currentFloor).removeRoom(room);
                }
                else {
                    room = model.getFloorPlan(currentFloor).roomWithColor(currentColor);
                    if (room == null) {
                        room = model.getFloorPlan(currentFloor).addRoomAt(r, c);
                        room.setColorIndex(currentColor);
                    }
                    else {
                        room.addTile(r, c);
                    }
                }
            }
        }
        //Select Room button q3
        else if(view.getDefineRoomsButton().isSelected()){
            Room room = model.getFloorPlan(currentFloor).roomAt(r, c);
            if (model.getFloorPlan(currentFloor).wallAt(r, c) || model.hasExitAt(r, c) || room == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Selection");
                alert.setHeaderText(null);
                alert.setContentText("You must select a tile that belongs to a room. ");
                alert.showAndWait();

            }
            else{
                R = model.getFloorPlan(currentFloor).roomAt(r,c); //room = new room();
                currentColor = model.getFloorPlan(currentFloor).roomAt(r,c).getColorIndex();
                Dialog dialog = new RoomInfoDialog(currentFloor,currentColor,R);
                dialog.showAndWait();
            }
            view.update(currentFloor, currentColor);
        }
        // Otherwise do nothing
        else {

            view.update(currentFloor, currentColor);
        }
    }
    public Room getR(){return R;}

    public static void main(String[] args) {
        launch(args);
    }
}