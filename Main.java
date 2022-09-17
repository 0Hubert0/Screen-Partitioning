package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        int quantity = 10;
        Color[] colors = {Color.YELLOW, Color.RED, Color.BLUE, Color.GREEN, Color.PURPLE, Color.PINK, Color.BROWN, Color.ORANGE};
        List<String> points = new ArrayList<>();
        AnchorPane root = new AnchorPane();

        Scene scene = new Scene(root, 900, 600, Color.BLACK);
        Canvas canvas = new Canvas(scene.getWidth(), scene.getHeight());
        GraphicsContext gr =canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);

            Color[] colors2 = new Color[quantity-8];
            for (int i = 0; i <quantity-8; i++) {
                colors2[i]=Color.rgb((int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256));
            }

        for (int i = 0; i <quantity; i++) {
            int x = (int)(Math.random()* scene.getWidth());
            int y = (int)(Math.random()* scene.getHeight());
            String g = x+";"+y;
            points.add(g);
        }

        for (int i = 0; i < scene.getWidth(); i++) {
            for (int j = 0; j <scene.getHeight(); j++) {
                double closest = 900*600;
                int index=-1;
                for (int k = 0; k <points.size(); k++) {
                    double attempt = isCloser(points.get(k), i, j, closest);
                    if(closest> attempt)
                    {
                        closest=attempt;
                        index=k;
                    }
                }
                if(index>=8)
                {
                    gr.setStroke(colors2[index-8]);
                }
                else
                {
                    gr.setStroke(colors[index]);
                }
                gr.beginPath();
                gr.lineTo(i, j);
                gr.stroke();
                //System.out.println(i+";"+j);
            }
        }

        gr.setLineWidth(3);
        gr.setStroke(Color.BLACK);
        for (int i = 0; i <quantity; i++) {
            String[] xy = points.get(i).split(";");
            gr.beginPath();
            gr.lineTo(Integer.parseInt(xy[0]), Integer.parseInt(xy[1]));
            gr.stroke();
        }

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

    }

    public static double isCloser(String string, int x, int y, double closest)
    {
        String[] coords = string.split(";");
        int x1 = Integer.parseInt(coords[0]);
        int y1 = Integer.parseInt(coords[1]);
        double distance = (x1-x)*(x1-x)+(y1-y)*(y1-y);
        if(distance<closest)
        {
            return distance;
        }
        return closest;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
