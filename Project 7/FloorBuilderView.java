import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class FloorBuilderView extends GridPane {

    private Button[][]     grid;
    private Label          layoutLabel, selectLabel, summaryLabel;
    private RadioButton[]   buttons;
    private Button       overviewButton;
    private Button       colorButton;
    private TextField    summary;

    private int colorIndex;

    private Building    model;
    private FloorPlan   p;
    private Building    g;

    GridPane aPane = new GridPane();
    GridPane aPane1 = new GridPane();

    public FloorBuilderView(Building m) { //create a single constructor

            model = m;
            p = FloorPlan.floor1();
            g = Building.example();
            grid = new Button[20][20];
            colorIndex = 0;

        //Buttons that are walls
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                Button b = new Button();

                grid[i][j] = b;
                if (p.wallAt(j, i)) {
                    b.setStyle("-fx-base: BLACK");
                }
                if (g.exitAt(j, i) != null) {
                    b.setText("EXIT");
                    b.setStyle("-fx-base: RED; -fx-font-size: 8");
                }
                b.setPrefWidth(Integer.MAX_VALUE);
                b.setPrefHeight(Integer.MAX_VALUE);

                b.setMinWidth(Integer.MIN_VALUE);
                b.setMinHeight(Integer.MIN_VALUE);
                setHgrow(b,Priority.ALWAYS);

                aPane.add(grid[i][j], i, j);

            }
        }
            setMargin(aPane, new Insets(5));
            add(aPane, 0, 1);


            //labels
            layoutLabel = new Label("FLOOR LAYOUT");
            selectLabel = new Label("SELECT/EDIT:");
            summaryLabel = new Label("FLOOR SUMMARY:");

            //radiobuttons
            ToggleGroup operations = new ToggleGroup();
            buttons = new RadioButton[4];
            String[] buttonLabels = {"Walls", "Exits", "Room Tiles", "Select Room"};
            int a = 0;
            for (int i = 0; i < 4; i++) {
                buttons[i] = new RadioButton(buttonLabels[i]);
                aPane1.add(buttons[i], 0, a++); //locate radiobuttons
                buttons[i].setToggleGroup(operations);
                setMargin(buttons[i], new Insets(5));

            }

            buttons[0].setSelected(true); //walls starts up

            //button
            overviewButton = new Button("Building Overview");

            //picture button
            colorButton = new Button();
            colorButton.setPrefSize(30, 30);
            colorButton.setDisable(true);
            colorButton.setStyle("-fx-base:ORANGE");



            //textfield
            summary = new TextField(p.getName() + " with " + p.getNumberOfRooms() + " rooms.");

            //sizing
            setPadding(new Insets(10));

            setMargin(layoutLabel, new Insets(5));
            add(layoutLabel, 0, 0);

            setMargin(selectLabel, new Insets(5));
            add(selectLabel, 1, 0);

            setMargin(summaryLabel, new Insets(5));
            add(summaryLabel, 0, 2,1,1);


            setMargin(overviewButton, new Insets(20, 5, 5, 5));
            aPane1.add(overviewButton, 0, 4, 1, 2);

            aPane1.add(colorButton, 1, 2);
            aPane1.setMinWidth(160);

            add(aPane1, 1, 1); //radio, colorbuttons and overview


            // Set Hgrow for TextField
        summary.setMinWidth(200);
        setHgrow(summary, Priority.ALWAYS);
        add(summary, 0,3,1,1);




    }
    public void update(){
        //model = m;
        p = model.getFloorPlans()[0];
        g = Building.example();

        //Buttons that are walls
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {

                Button b = grid[i][j];
                b.setStyle("-fx-base: WHITE");
                b.setText("");

                if (p.wallAt(j, i)) {
                    b.setStyle("-fx-base: BLACK");

                }
                if (model.exitAt(j, i) != null || g.exitAt(j, i) != null) {
                    b.setText("EXIT");
                    b.setStyle("-fx-base: RED; -fx-font-size: 8");
                }
                if(model.getFloorPlans()[0].roomAt(j,i) != null){
                    b.setStyle("-fx-base:" + FloorBuilderApp.ROOM_COLORS[model.getFloorPlans()[0].roomAt(j,i).getColorIndex()]);

                }




                b.setPrefWidth(Integer.MAX_VALUE);
                b.setPrefHeight(Integer.MAX_VALUE);

                b.setMinWidth(Integer.MIN_VALUE);
                b.setMinHeight(Integer.MIN_VALUE);


            }
        }


        //labels
        layoutLabel = new Label("FLOOR LAYOUT");
        selectLabel = new Label("SELECT/EDIT:");
        summaryLabel = new Label("FLOOR SUMMARY:");

        //radiobuttons
        ToggleGroup operations = new ToggleGroup();
        buttons = new RadioButton[4];
        String[] buttonLabels = {"Walls", "Exits", "Room Tiles", "Select Room"};
        int a = 0;
        for (int i = 0; i < 4; i++) {
            buttons[i] = new RadioButton(buttonLabels[i]);
            buttons[i].setToggleGroup(operations);

        }


        //button
        overviewButton = new Button("Building Overview");



        //textfield
        summary = new TextField(model.getFloorPlans()[0].getName() + " with " + model.getFloorPlans()[0].getNumberOfRooms() + " rooms.");





    }

    //get methods
    public Button getOverviewButton(){return overviewButton;}
    public RadioButton getButtons(int i){return  buttons[i];}
    public Button getColorButton(){return colorButton;}
    public Button getGrid(int i, int j){return grid[i][j];}
    public int getColorIndex() {return colorIndex;}
    public void setColorIndex(int c){colorIndex = c;}
    public Building getModel(){return model;}





}
