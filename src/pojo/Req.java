package pojo;

import java.util.Date;
import java.util.Objects;

public class Req {
   private Integer timeDay;
   private String state;
   private String reason;
   private String username;
   private String name;
   private Date startTime;
   private Integer id;

    public Req() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Integer getTimeDay() {
        return timeDay;
    }

    public void setTimeDay(Integer timeDay) {
        this.timeDay = timeDay;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Req{" +
                "timeDay=" + timeDay +
                ", state='" + state + '\'' +
                ", reason='" + reason + '\'' +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", startTime=" + startTime +
                ", id=" + id +
                '}';
    }

}
