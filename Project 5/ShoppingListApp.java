import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage; //740, 390

import javafx.event.*;

public class ShoppingListApp extends Application {

    Pane aPane;
    Shopper c = new Shopper();

    public void start(Stage primaryStage) {

        aPane = new Pane();
        //window
        primaryStage.setTitle("Grocery Store Application");
        primaryStage.setScene(new Scene(aPane, 730, 380));
        primaryStage.setResizable(false);
        primaryStage.show();
        update();

    }

    GroceryItem[] products = {
            new FreezerItem("Smart-Ones Frozen Entrees", 1.99f, 0.311f),
            new GroceryItem("SnackPack Pudding", 0.99f, 0.396f),
            new FreezerItem("Breyers Chocolate Icecream", 2.99f, 2.27f),
            new GroceryItem("Nabob Coffee", 3.99f, 0.326f),
            new GroceryItem("Gold Seal Salmon", 1.99f, 0.213f),
            new GroceryItem("Ocean Spray Cranberry Cocktail", 2.99f, 2.26f),
            new GroceryItem("Heinz Beans Original", 0.79f, 0.477f),
            new RefrigeratorItem("Lean Ground Beef", 4.94f, 0.75f),
            new FreezerItem("5-Alive Frozen Juice", 0.75f, 0.426f),
            new GroceryItem("Coca-Cola 12-pack", 3.49f, 5.112f),
            new GroceryItem("Toilet Paper - 48 pack", 40.96f, 10.89f),
            new RefrigeratorItem("2L Sealtest Milk", 2.99f, 2.06f),
            new RefrigeratorItem("Extra-Large Eggs", 1.79f, 0.77f),
            new RefrigeratorItem("Yoplait Yogurt 6-pack", 4.74f, 1.02f),
            new FreezerItem("Mega-Sized Chocolate Icecream", 67.93f, 15.03f)
    };

    public static void main(String[] args) {
        launch(args);
    }



    public void update(){

        //restart button last question
        Button restart = new Button();


        //Create all 3 buttons
        Button buybutton = new Button("Buy");
        buybutton.relocate(10, 355);
        buybutton.setPrefSize(200, 25);

        Button returnbutton = new Button("Return");
        returnbutton.relocate(220, 355);
        returnbutton.setPrefSize(200, 25);

        Button checkoutbutton = new Button("Checkout");
        checkoutbutton.relocate(430, 355);
        checkoutbutton.setPrefSize(120, 25);


        //create all 3 ListViews
        ListView<String> ProductsList = new ListView<String>();
        ProductsList.relocate(10, 45);
        ProductsList.setPrefSize(200, 300);

        ListView<String> ShoppingCartList = new ListView<String>(FXCollections.observableArrayList());
        ShoppingCartList.relocate(220, 45);
        ShoppingCartList.setPrefSize(200, 300);

        ListView<String> ContentsList = new ListView<String>();
        ContentsList.relocate(430, 45);
        ContentsList.setPrefSize(300, 300);

        // Create all 3 labels
        Label label1 = new Label("Products");
        label1.relocate(10, 10);

        Label label2 = new Label("Shopping Cart");
        label2.relocate(220, 10);

        Label label3 = new Label("Contents");
        label3.relocate(430, 10);

        //create textfield and its label
        TextField priceField = new TextField("$0.00");
        priceField.setAlignment(Pos.CENTER_RIGHT); //q2
        priceField.relocate(630, 355);
        priceField.setPrefSize(100, 25);

        Label price = new Label("Total Price:");
        price.relocate(565, 355);

        //q2 all buttons disabled
        buybutton.setDisable(true);
        returnbutton.setDisable(true);
        checkoutbutton.setDisable(true);

        //q2 products list
        String[] prod = new String[products.length];
        for (int i = 0; i < products.length; i++) {
            prod[i] = products[i].toString();

        }
        ProductsList.setItems(FXCollections.observableArrayList(prod));

        //enable/disable the add button accordingly
        ProductsList.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                buybutton.setDisable(ProductsList.getSelectionModel().getSelectedIndex()<0);
            }
        });

        //buybutton and Total Price
        buybutton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                c.addItem(products[ProductsList.getSelectionModel().getSelectedIndex()]);

                String[] shopping = new String[c.NumItems];
                Float total=0f;
                for (int i = 0; i < c.NumItems; i++) {
                    shopping[i] = c.cart[i].getDescription();
                    total = total + c.cart[i].getPrice();

                }
                priceField.setText(String.format("$%1.2f",total));
                ShoppingCartList.setItems(FXCollections.observableArrayList(shopping));

                // Checkout button is enabled
                if(shopping.length>0){
                    checkoutbutton.setDisable(false);
                }else{
                    checkoutbutton.setDisable(true);
                }

            }
        });


        // Return button is enabled
        ShoppingCartList.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                returnbutton.setDisable(ShoppingCartList.getSelectionModel().getSelectedIndex()<0);

            }
        });

        //Removed from the shopping cart
        returnbutton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                c.removeItem(c.cart[ShoppingCartList.getSelectionModel().getSelectedIndex()]);

                String[] shopping = new String[c.NumItems];
                for (int i = 0; i < c.NumItems; i++) {
                    shopping[i] = c.cart[i].getDescription();

                }
                priceField.setText(String.format("$%1.2f",c.computeTotalCost()));
                ShoppingCartList.setItems(FXCollections.observableArrayList(shopping));

                // Checkout button is enabled
                if(shopping.length>0){
                    checkoutbutton.setDisable(false);
                }else{
                    checkoutbutton.setDisable(true);
                }

            }
        });

        //checkout clicked
        checkoutbutton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                //receipt
                for(int i =0; i<c.NumItems; i++) {
                        System.out.println(String.format("%-30s%10.2f", c.cart[i].getDescription(), c.cart[i].getPrice()));

                }
                System.out.println("----------------------------------------");
                System.out.println(String.format("%-30s%10.2f","TOTAL",c.computeTotalCost()));

                c.packBags();
                String[] packeddes =  new String[c.NumItems];
                for (int i = 0; i < c.NumItems; i++) {
                    packeddes[i] = c.cart[i].getDescription();

                }
                priceField.setText(String.format("$%1.2f",c.computeTotalCost()));
                ShoppingCartList.setItems(FXCollections.observableArrayList(packeddes));

                //Grocerybags Selected
                ShoppingCartList.setOnMousePressed(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        ContentsList.setItems(FXCollections.observableArrayList(c.cart[ShoppingCartList.getSelectionModel().getSelectedIndex()].getContents()));

                    }
                });

                //Buy and Return buttons should be disabled and the Products list should be disabled
                ProductsList.setDisable(true);
                buybutton.setDisable(true);
                returnbutton.setDisable(true);

                //the Checkout button should have its text changed to "Restart Shopping"
                for(int i=0;i<c.getNumItems(); i++)
                    c.removeItem(c.cart[i]);
                checkoutbutton.setDisable(true);
                restart.setText("Restart Shopping");
                restart.relocate(430, 355);
                restart.setPrefSize(120, 25);
                restart.setDisable(false);
                aPane.getChildren().add(restart);


            }
        });
        //Restart Button pressed
        restart.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                //clear stuff up
                restart.setText("");
                update();


            }
        });


        // Add all the components to the window
        aPane.getChildren().addAll(buybutton, returnbutton, checkoutbutton,
                ProductsList, ShoppingCartList, ContentsList,
                label1, label2, label3,
                priceField, price);
    }



}
