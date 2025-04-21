package com.crio.xlido.entities;

// public class Event {
//     private Long id;
//     private String name;
//     private Long userId;
    
//     public Event(String name, Long userId){
//         this.id = null;
//         this.name = name;
//         this.userId = userId;
//     }

//     public Event(Long id, Event other) {
//         this.id = id;
//         this.name = other.name;
//         this.userId = other.userId;
//     }

//     public Long getId(){
//         return id;
//     }

//     public void setId(Long id){
//         this.id= id;
//     }

//     public String getName(){
//         return name;
//     }

//     public void setName(String name){
//         this.name = name;
//     }

//     public Long getUserId(){
//         return userId;
//     }

//     public void setUserId(Long userId){
//         this.userId = userId;
//     }

//     @Override
//     public String toString(){
//         return "Event ID: " +id;
//     }
// }

public class Event {
    private final Long id;
    private final String title;
    private final Long organizerId;

    public Event(String title, Long organizerId) {
        this.id = null; // ID will be set by the repository
        this.title = title;
        this.organizerId = organizerId;
    }

    public Event(Long id, Event other) {
        this.id = id;
        this.title = other.title;
        this.organizerId = other.organizerId;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Long getOrganizerId() {
        return organizerId;
    }

    @Override
    public String toString() {
        return "Event [id=" + id + ", title=" + title + ", organizerId=" + organizerId + "]";
    }

  
}
