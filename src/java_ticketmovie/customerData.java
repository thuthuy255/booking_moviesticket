/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java_ticketmovie;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author ngthuthuy
 */
public class customerData {

    private Integer id;
    private String type;
    private String title;
    private double total;
    private Date date;
    private Time time;
    customerData() {
    throw new UnsupportedOperationException("Not supported yet.");
    }

    public customerData(Integer id, String type,String title, double total, Date date,Time time) {
        this.id = id;
        this.type = type;
        this.title=title;
        this.total = total;
        this.date = date;
        this.time = time;
    }





    public Integer getId() {
        return this.id;
    }

    public String getType() {
        return this.type;
    }
    public String getTitle(){
        return this.title;
    }

    public double getTotal() {
        return this.total;
    }

    public Date getDate() {
        return this.date;
    }

    public Time getTime() {
        return this.time;
    }
}
