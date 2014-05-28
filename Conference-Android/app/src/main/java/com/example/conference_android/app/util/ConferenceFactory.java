package com.example.conference_android.app.util;

import com.example.conference_android.app.model.Conference;
import com.example.conference_android.app.model.Event;
import com.example.conference_android.app.model.EventLeader;
import com.example.conference_android.app.model.Room;
import com.example.conference_android.app.model.ScheduledEvent;
import com.example.conference_android.app.model.Sponsor;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by carlushenry on 5/28/14.
 */
public class ConferenceFactory {
    private static RandomData random = new RandomData();
    private static List<Room> rooms;
    private static List<Event> events;
    private static List<EventLeader> eventLeaders;

    public static Conference createConference() {
        Conference conf = new Conference();
        conf.setDescription(random.getRandomString("Description:"));
        conf.setEndDttm(new Date());
        conf.setId(random.getRandomLong());
        conf.setName(random.getRandomString("Name:"));
        conf.setStartDttm(new Date());

        conf.setSponsors(createSponsors(conf.getId(), 3));
        conf.setScheduledEvents(createScheduledEvents(conf.getId(), 10));

        return conf;
    }

    private static List<ScheduledEvent> createScheduledEvents(Long conferenceId, int scheduledEventCount) {
        List<ScheduledEvent> scheduledEvents = new ArrayList<ScheduledEvent>();
        int roomCount = 10;
        int eventCount = 10;
        int eventLeaderCount = 10;

        rooms = createRooms(roomCount);
        events = createEvents(conferenceId, eventCount);
        eventLeaders = createEventLeaders(conferenceId, eventLeaderCount);

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, Calendar.JUNE);
        cal.set(Calendar.DATE, 25);
        cal.set(Calendar.YEAR, 2014);
        cal.set(Calendar.HOUR_OF_DAY, 13);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);

        Date currStartDateTime = cal.getTime();

        for (int i = 0; i < scheduledEventCount; i++) {
            ScheduledEvent currScheduledEvent = new ScheduledEvent();

            Date endTime = addHourTo(currStartDateTime);
            currScheduledEvent.set_id(random.getRandomLong());
            currScheduledEvent.setConferenceId(conferenceId);
            currScheduledEvent.setStartDttm(currStartDateTime);
            currScheduledEvent.setEndDttm(endTime);

            int eventIndex = new Random().nextInt(eventCount + 1);
            int roomIndex = new Random().nextInt(roomCount + 1);
            int eventLeaderIndex = new Random().nextInt(eventLeaderCount + 1);

            currScheduledEvent.setEventId(events.get(eventIndex).get_id());
            currScheduledEvent.setEventLeaderId(eventLeaders.get(eventLeaderIndex).get_id());
            currScheduledEvent.setRoomId(rooms.get(roomIndex).get_id());

            scheduledEvents.add(currScheduledEvent);
            currStartDateTime = endTime;
        }

        return scheduledEvents;

    }

    private static Date addHourTo(Date currStartDateTime) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(currStartDateTime);
        cal.set(Calendar.HOUR, cal.get(Calendar.HOUR) + 1);
        return cal.getTime();
    }

    private static List<EventLeader> createEventLeaders(Long conferenceId, int eventLeaderCount) {
        List<EventLeader> eventLeaders = new ArrayList<EventLeader>();

        for (int i = 0; i < eventLeaderCount; i++) {
            EventLeader currEventLeader = new EventLeader();

            currEventLeader.setConferenceId(conferenceId);
            currEventLeader.set_id(random.getRandomLong());
            currEventLeader.setBiography(random.getRandomString("Bio"));
            currEventLeader.setFirstName(random.getRandomString("FirstName"));
            currEventLeader.setLastName(random.getRandomString("LastName"));

            eventLeaders.add(currEventLeader);
        }

        return eventLeaders;
    }

    private static List<Event> createEvents(Long conferenceId, int eventCount) {
        List<Event> events = new ArrayList<Event>();

        for (int i = 0 ; i < eventCount; i++) {
            Event event = new Event();

            event.set_id(random.getRandomLong());
            event.setDescription(random.getRandomString("Description: "));
            event.setConferenceId(conferenceId);
            event.setEventType(random.getRandomString("EventType: "));
            event.setTitle(random.getRandomString("Title: "));

            events.add(event);
        }

        return events;
    }

    private static List<Room> createRooms(int roomCount) {
        List<Room> rooms = new ArrayList<Room>();

        for (int i = 0; i < roomCount; i++) {
            Room currRoom = new Room();

            currRoom.setDescription(random.getRandomString("RoomDescription:"));
            currRoom.setName(random.getRandomString("Room Name: "));
            currRoom.set_id(random.getRandomLong());
            currRoom.setLocationInfo(random.getRandomString("LocationInfo:"));

            rooms.add(currRoom);
        }
        return rooms;
    }

    private static List<Sponsor> createSponsors(Long conferenceId, int count) {
        List<Sponsor> sponsors = new ArrayList<Sponsor>();

        for (int i = 0; i < count; i++) {
            Sponsor currSponsor = new Sponsor();
            currSponsor.setName(random.getRandomString("Name:"));
            currSponsor.setId(random.getRandomLong());
            currSponsor.setDescription(random.getRandomString("Desc: "));
            currSponsor.setConferenceId(conferenceId);
            currSponsor.setLogo(random.getRandomString("Logo:"));
            currSponsor.setSponsorshipLevel(random.getRandomString("SponsorshipLevel:"));
        }
        return sponsors;
    }

    private static class RandomData {
        public String getRandomString(String prefix) {
            return String.format("%s%d", prefix, getRandomLong());
        }

        public Long getRandomLong() {
            return new Random().nextLong();
        }
    }

    public static List<Room> getRooms() {
        return rooms;
    }

    public static void setRooms(List<Room> rooms) {
        ConferenceFactory.rooms = rooms;
    }

    public static List<Event> getEvents() {
        return events;
    }

    public static void setEvents(List<Event> events) {
        ConferenceFactory.events = events;
    }

    public static List<EventLeader> getEventLeaders() {
        return eventLeaders;
    }

    public static void setEventLeaders(List<EventLeader> eventLeaders) {
        ConferenceFactory.eventLeaders = eventLeaders;
    }
}
