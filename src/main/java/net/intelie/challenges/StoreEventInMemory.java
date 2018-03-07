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
 * In this challenge I decided not to use java libraries as ArrayList. So I
 * built my own eventList, in order to dinamically store data in the eventList 
 * and have a better efficiency in memory allocation I used a linked list structure 
 * and an auxiliar class called EventNode that
 * helps me with the linked list.
 */
public class StoreEventInMemory implements EventStore {

    private EventList eventList;

    StoreEventInMemory() {

        eventList = new EventList();

    }

    /**
     * Add an event in the eventList
     *
     * @param event
     *
     * Synchronization is used in order to avoid inconsistency in the list in
     * case another process tries to use this source.
     */
    @Override
    public synchronized void insert(Event event) {

        eventList.add(event);
    }

    @Override
    public synchronized void removeAll(String type) {

        EventIterator iterator;
        iterator = eventList;
        // I used this first while loop because the iterator moveNext doesn´t
        // check the first element of the list
        while (iterator.current().type().equals(type)) {
            iterator.remove();
        }
        while (iterator.moveNext()) {
            while (iterator.current().type().equals(type)) {
                iterator.remove();
            }
        }
        //This condition checks the last element of the list.
        if (iterator.current().type().equals(type)) {
            iterator.remove();
        }

    }

    @Override
    // Synchronization is used because this method uses a shared source (eventList)
    public synchronized EventIterator query(String type, long startTime, long endTime) {

        EventIterator i = eventList;
        // I used this first while loop because the iterator moveNext don't
        // check the first element of the list
        while (!this.verified(i, type, startTime, endTime)) {
            i.remove();
        }

        while (i.moveNext()) {
            while (!this.verified(i, type, startTime, endTime)) {
                i.remove();
            }
        }
        //This condition checks the last element of the list.
        if (!this.verified(i, type, startTime, endTime)) {
            i.remove();
        }

        return i;
    }

    /**
     * Verify the condition sentence
     *
     * @param i An event Iterator.
     * @param type The type we are querying for.
     * @param startTime Start timestamp (inclusive).
     * @param endTime End timestamp (exclusive).
     * @return A boolean value: returns true if event type value equals the
     * param type values and even timestamp between {
     * @param startTime} (inclusive) and {
     * @param endTime} (exclusive).
     */
    private boolean verified(EventIterator i, String type, long startTime, long endTime) {

        return (i.current().type().equals(type)) && (i.current().timestamp() >= startTime) && (i.current().timestamp() < endTime);
    }

}
