package com.sagetech.conference_android.app.model;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by danmikita on 6/2/14.
 */
public class EventData implements Serializable {

    private boolean header;

    @Expose
    private Integer id;
    @Expose
    private String start_dttm;
    @Expose
    private String end_dttm;
    @Expose
    private Room room;
    @Expose
    private Event event;
    @Expose
    private List<Event_leader> event_leaders = new ArrayList<Event_leader>();
    @Expose
    private Boolean chosen_by_attendee;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStart_dttm() {
        return start_dttm;
    }

    public void setStart_dttm(String start_dttm) {
        this.start_dttm = start_dttm;
    }

    public String getEnd_dttm() {
        return end_dttm;
    }

    public void setEnd_dttm(String end_dttm) {
        this.end_dttm = end_dttm;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public List<Event_leader> getEvent_leaders() {
        return event_leaders;
    }

    public void setEvent_leaders(List<Event_leader> event_leaders) {
        this.event_leaders = event_leaders;
    }

    public Boolean getChosen_by_attendee() {
        return chosen_by_attendee;
    }

    public void setChosen_by_attendee(Boolean chosen_by_attendee) {
        this.chosen_by_attendee = chosen_by_attendee;
    }

    public boolean isHeader() {
        return header;
    }

    public void setHeader(boolean header) {
        this.header = header;
    }

    public static class Event {

        @Expose
        private Integer id;
        @Expose
        private String title;
        @Expose
        private String description;
        @Expose
        private String event_type;
        @Expose
        private String created_at;
        @Expose
        private String updated_at;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getEvent_type() {
            return event_type;
        }

        public void setEvent_type(String event_type) {
            this.event_type = event_type;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

    }
    public static class Event_leader {

        @Expose
        private Integer id;
        @Expose
        private String first_name;
        @Expose
        private String last_name;
        @Expose
        private String biography;
        @Expose
        private String created_at;
        @Expose
        private String updated_at;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getFirst_name() {
            return first_name;
        }

        public void setFirst_name(String first_name) {
            this.first_name = first_name;
        }

        public String getLast_name() {
            return last_name;
        }

        public void setLast_name(String last_name) {
            this.last_name = last_name;
        }

        public String getBiography() {
            return biography;
        }

        public void setBiography(String biography) {
            this.biography = biography;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

    }

    public static class Room {

        @Expose
        private Integer id;
        @Expose
        private String name;
        @Expose
        private String description;
        @Expose
        private String location_info;
        @Expose
        private String created_at;
        @Expose
        private String updated_at;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getLocation_info() {
            return location_info;
        }

        public void setLocation_info(String location_info) {
            this.location_info = location_info;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

    }
}
