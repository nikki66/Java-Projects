import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class FloorBuilderApp extends Application {
    private Building model;
    private FloorBuilderView view;
    private int                 lastI;
    private int                 lastJ;




    public static final String[] ROOM_COLORS = {"ORANGE", "YELLOW", "LIGHTGREEN", "DARKGREEN",
            "LIGHTBLUE", "BLUE", "CYAN", "DARKCYAN",
            "PINK", "DARKRED", "PURPLE", "GRAY"};



    public void start(Stage primaryStage) {
        model = new Building(model.MAXIMUM_FLOORPLANS, model.MAXIMUM_EXITS);
        view = new FloorBuilderView(model);
        model.getFloorPlans()[0] = FloorPlan.floor1();



        //colorbutton enabled
        view.getButtons(2).setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                view.getColorButton().setDisable(false);
                view.update();

            }
        });

        //colorbutton pressed
        view.getColorButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                int color = view.getColorIndex();
                if(color==11){
                    color =0;
                    view.getColorButton().setStyle("-fx-base:"+ ROOM_COLORS[color]);
                }else{
                    color++;
                    view.getColorButton().setStyle("-fx-base:"+ ROOM_COLORS[color]);
                }

                view.setColorIndex(color);
                view.update();

            }
        });


        //wall
        view.getButtons(0).setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                view.getColorButton().setDisable(true);
                view.update();
            }
        });

        //wall-to-nonwall
        view.getButtons(0).setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
            for (int i = 0; i < model.getFloorPlans()[0].size(); i++) {
                for (int j = 0; j < model.getFloorPlans()[0].size(); j++) {

                    view.getGrid(i, j).setOnAction(new EventHandler<ActionEvent>() {
                        public void handle(ActionEvent event) {
                            handle1(event);
                            if (model.getFloorPlans()[0].wallAt(lastJ, lastI)) {
                                model.getFloorPlans()[0].setWallAt(lastJ, lastI, false);


                            }
                            else if(model.hasExitAt(lastJ,lastI)){
                                model.removeExit(lastJ,lastI);
                                model.getFloorPlans()[0].setWallAt(lastJ, lastI, true);


                            } else if(model.getFloorPlans()[0].roomAt(lastJ,lastI) !=null){
                                model.getFloorPlans()[0].roomAt(lastJ,lastI).removeTile(lastJ,lastI);
                                model.getFloorPlans()[0].setWallAt(lastJ, lastI, true);

                            }
                            else{
                                model.getFloorPlans()[0].setWallAt(lastJ, lastI, true);

                            }
                            view.update();
                        }


                    });

                }
            }view.update();
            }
        });
        //Exit
        view.getButtons(1).setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                view.getColorButton().setDisable(true);
                for (int i = 0; i < model.getFloorPlans()[0].size(); i++) {
                    for (int j = 0; j < model.getFloorPlans()[0].size(); j++) {
                        view.getGrid(i, j).setOnAction(new EventHandler<ActionEvent>() {
                            public void handle(ActionEvent event) {
                                handle1(event);
                                if (model.hasExitAt(lastJ, lastI)) {
                                    model.removeExit(lastJ, lastI);

                                } else if(model.getFloorPlans()[0].roomAt(lastJ,lastI) !=null){
                                    model.getFloorPlans()[0].roomAt(lastJ,lastI).removeTile(lastJ,lastI);
                                    model.addExit(lastJ, lastI);

                                }
                                else {
                                    model.addExit(lastJ, lastI);

                                }
                                view.update();
                            }


                        });

                    }
                }
                view.update();
            }
        });
        //Room
        view.getButtons(2).setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                view.getColorButton().setDisable(false);
                for (int i = 0; i < model.getFloorPlans()[0].size(); i++) {
                    for (int j = 0; j < model.getFloorPlans()[0].size(); j++) {
                        view.getGrid(i, j).setOnAction(new EventHandler<ActionEvent>() {
                            public void handle(ActionEvent event) {
                                handle1(event);
                                if (model.hasExitAt(lastJ, lastI)
                                        || model.getFloorPlans()[0].wallAt(lastJ, lastI)) {
                                        ;

                                }else{
                                     if(model.getFloorPlans()[0].roomAt(lastJ,lastI) == null){
                                         if(model.getFloorPlans()[0].getNumberOfRooms() > 12|| model.getFloorPlans()[0].getNumberOfRooms() < 12){
                                         model.getFloorPlans()[0].addRoomAt(lastJ,lastI);
                                         model.getFloorPlans()[0].roomAt(lastJ,lastI).setColorIndex(view.getColorIndex());}

                                     }else if(model.getFloorPlans()[0].roomAt(lastJ,lastI) !=null) {
                                         if(model.getFloorPlans()[0].roomAt(lastJ,lastI).getColorIndex() == view.getColorIndex()){
                                             model.getFloorPlans()[0].roomAt(lastJ,lastI).removeTile(lastJ,lastI);
                                         }
                                         else{
//                                            model.getFloorPlans()[0].roomAt(lastJ,lastI).removeTile(lastJ,lastI);
                                             model.getFloorPlans()[0].roomAt(lastJ,lastI).setColorIndex(view.getColorIndex());

                                         }


                                     }
                                }
                                view.update();
                            }



                        });

                    }
                }
                view.update();
            }
        });

        //Select Room
        view.getButtons(3).setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                view.getColorButton().setDisable(true);
                view.update();
            }
        });




        primaryStage.setTitle("Floor Plan Builder");
        primaryStage.setResizable(true);
        primaryStage.setScene(new Scene(view,400, 300));
        primaryStage.show();

        view.update();

    }


   public void handle1(ActionEvent event) {
       int i;
       int j;
       for (i = 0; i < model.getFloorPlans()[0].size(); i++) {
           for (j = 0; j < model.getFloorPlans()[0].size(); j++) {
               int finalJ = j;
               int finalI = i;
               if (event.getSource() == view.getGrid(finalI, finalJ)) {
                   lastI = finalI;
                   lastJ = finalJ;


               }

           }
       }
   }


    public static void main(String[] args) {
        launch(args);
    }
}


