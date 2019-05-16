package controller;

import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.function.BiConsumer;

import dao.StatisticsDAO;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import model.ClassDTO.FavoriteBook;

public class StatisticsController implements Initializable{
	StatisticsDAO statisticsDAO = new StatisticsDAO();
	@FXML
	private PieChart pieChartTab1;
	@FXML
	private VBox vBoxTab1;
	@FXML
	private BarChart<Date, Number> barChartTable2;
	
	@FXML
	private Label lbName1,lbName2,lbName3,lbName4,lbName5,lbName6;
	@FXML
	private Label lbAuthor1,lbAuthor2,lbAuthor3,lbAuthor4,lbAuthor5,lbAuthor6;
	@FXML
	private Label lbCount1,lbCount2,lbCount3,lbCount4,lbCount5,lbCount6;
	
	@FXML
	private BorderPane borderPaneTab1;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		initTab1();
		initTab2();
	}
	
	public void initTab1() {
		
		Map<String, Integer> listCategory = statisticsDAO.getTotalBookByCategory();
		pieChartTab1.setStartAngle(90);
		listCategory.forEach(new BiConsumer<String, Integer>() {
			private int index=0;
			private int otherTotal=0;
			private int total=0;
			@Override
			public void accept(String t, Integer u) {
				total+=u;
				if(index == listCategory.size()-1) {
					pieChartTab1.getData().add(new PieChart.Data("Other", otherTotal));
					pieChartTab1.setTitle("Total: " + total);
				}else if(index<5) {
					pieChartTab1.getData().add(new PieChart.Data(t, u));
				}
				else{
					otherTotal+=u;
				}
				index++;
			}
		});
		
		 final Label caption = new Label("");
		 caption.setTextFill(Color.AQUAMARINE);
	      caption.setStyle("-fx-font: 12 arial;");
		
		for (PieChart.Data data : pieChartTab1.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    caption.setTranslateX(e.getSceneX());
                    caption.setTranslateY(e.getSceneY());
                    caption.setText(String.valueOf(data.getPieValue()));
                    
                }
            });
        }
		borderPaneTab1.getChildren().add(caption);
	}
	
	
	public void initTab2() {
		List<FavoriteBook> list = statisticsDAO.getFavoriteBook();
		FavoriteBook fb;
		fb = list.get(0);
		lbName1.setText(fb.getName());
		lbAuthor1.setText(fb.getAuthor());
		lbCount1.setText(fb.getCount() + " times" );
		lbName1.setTooltip(new Tooltip(lbName1.getText()));
		
		fb = list.get(1);
		lbName2.setText(fb.getName());
		lbAuthor2.setText(fb.getAuthor());
		lbCount2.setText(fb.getCount() + " times" );
		lbName2.setTooltip(new Tooltip(lbName2.getText()));
		
		fb = list.get(2);
		lbName3.setText(fb.getName());
		lbAuthor3.setText(fb.getAuthor());
		lbCount3.setText(fb.getCount() + " times" );
		lbName3.setTooltip(new Tooltip(lbName3.getText()));
		
		fb = list.get(3);
		lbName4.setText(fb.getName());
		lbAuthor4.setText(fb.getAuthor());
		lbCount4.setText(fb.getCount() + " times" );
		lbName4.setTooltip(new Tooltip(lbName4.getText()));
		
		fb = list.get(4);
		lbName5.setText(fb.getName());
		lbAuthor5.setText(fb.getAuthor());
		lbCount5.setText(fb.getCount() + " times" );
		lbName5.setTooltip(new Tooltip(lbName5.getText()));
		
		fb = list.get(5);
		lbName6.setText(fb.getName());
		lbAuthor6.setText(fb.getAuthor());
		lbCount6.setText(fb.getCount() + " times" );
		lbName6.setTooltip(new Tooltip(lbName6.getText()));
	}
}
