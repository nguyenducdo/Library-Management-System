package view.StageBorrowInfo;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import model.ClassDTO.SelectedBook;

public class DatePickerCell<S, T> extends TableCell<SelectedBook, Date> {

    private DatePicker datePicker;
    private ObservableList<SelectedBook> listSelectedBook;

    public DatePickerCell(ObservableList<SelectedBook> listSelectedBook) {

        super();
        
        this.listSelectedBook = listSelectedBook;

        if (datePicker == null) {
            createDatePicker();
        }
        datePicker.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();
                setDisable(empty || date.compareTo(today) < 0 );
            }
        });
        setGraphic(datePicker);
        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        
    }

    @Override
    public void updateItem(Date item, boolean empty) {

        super.updateItem(item, empty);
        if (this.datePicker == null) {
            System.out.println("datePicker is NULL");
        }

        if (empty) {
            setText(null);
            setGraphic(null);
        } else {

            if (isEditing()) {
                setContentDisplay(ContentDisplay.TEXT_ONLY);

            } else {
                setGraphic(this.datePicker);
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            }
        }
    }

    private void setDatepikerDate(Date date) {
        datePicker.setValue(date.toLocalDate());
    }

    private void createDatePicker() {
        this.datePicker = new DatePicker();
        datePicker.setEditable(false);
        

        datePicker.setOnAction(event -> {
			// TODO Auto-generated method stub
			LocalDate date = datePicker.getValue();
		    int index = getIndex();

		    setText(date.toString());
		    commitEdit(Date.valueOf(date));
		    if(getListSelectedBook()!=null) {
		    	getListSelectedBook().get(index).setReturnDate(Date.valueOf(date));
		    	for (SelectedBook selectedBook : listSelectedBook) {
		    		System.out.println(selectedBook);
				}
		    }
		});

        setAlignment(Pos.CENTER);
    }

    @Override
    public void startEdit() {
        super.startEdit();
    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();
        setContentDisplay(ContentDisplay.TEXT_ONLY);
    }
    

    public DatePicker getDatePicker() {
        return datePicker;
    }

    public void setDatePicker(DatePicker datePicker) {
        this.datePicker = datePicker;
    }

	public ObservableList<SelectedBook> getListSelectedBook() {
		return listSelectedBook;
	}


	public void setListSelectedBook(ObservableList<SelectedBook> listSelectedBook) {
		this.listSelectedBook = listSelectedBook;
	}

}
