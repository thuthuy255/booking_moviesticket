package java_ticketmovie;

import java.sql.Date;

public class moviesData {
    private Integer id;
    private String movieTitle;
    private String genre;
    private String duration;
    private String image;
    private Date date;
    private String current;

    public moviesData(Integer id, String movieTitle, String genre, String duration, String image, Date date, String current) {
        this.id = id;
        this.movieTitle = movieTitle;
        this.genre = genre;
        this.duration = duration;
        this.image = image;
        this.date = date;
        this.current=current;
    }

    moviesData() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Integer getId() {
        return this.id;
    }

    public String getTitle() {
        return this.movieTitle;
    }

    public String getGenre() {
        return this.genre;
    }

    public String getDuration() {
        return this.duration;
    }

    public String getImage() {
        return this.image;
    }

    public Date getDate() {
        return this.date;
    }
    public String getCurrent(){
        return this.current;
    }
}

