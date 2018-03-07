/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste_intelie;

/**
 *
 * @author Humberto
 * 
 * Auxiliary class to help in the eventList
 */
public class EventNode {

    private Event content;
    private EventNode next;
    private EventNode before;

    EventNode(Event content) {
        this.content = content;
        this.next = null;
        this.before = null;
    }

    public EventNode getNext() {
        return next;
    }

    public void setNext(EventNode next) {
        this.next = next;
    }

    public Event getContent() {
        return content;
    }

    public EventNode getBefore() {
        return before;
    }

    public void setBefore(EventNode before) {
        this.before = before;
    }

}
