/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste_intelie;

/**
 *
 * @author Humberto
 */
public class EventList implements EventIterator {

    private EventNode first;
    private EventNode currentNode;
    private EventNode last;

    EventList() {
        this.first = null;
        this.last = null;
        this.currentNode = null;
    }
    /**
     * Add an event in the list.
     *
     * @param event
     */
    
    public void add(Event event) {
        EventNode node = new EventNode(event);
        if (this.first == null) {
            this.first = this.last = this.currentNode = node;
        } else {
            this.first.setBefore(node);
            this.first.getBefore().setNext(this.first);
            this.first = this.currentNode = node;
        }
    }

    @Override
    public boolean moveNext() {
        if (this.currentNode.getNext() != null) {
            this.currentNode = this.currentNode.getNext();
            return true;
        }
        this.currentNode = this.first;
        return false;
    }

    @Override
    public Event current() {
        return this.currentNode.getContent();
    }
    
    
    @Override
    public void remove() {

        if ((this.currentNode.getBefore() == null) && (this.currentNode.getNext() == null)) {
            this.first = this.last = this.currentNode = null;
            return;
        }
        if (this.currentNode.getBefore() == null) {
            this.first = this.first.getNext();
            this.first.setBefore(null);
            this.currentNode = this.first;
            return;
        }
        if (this.currentNode.getNext() == null) {
            this.last = this.currentNode.getBefore();
            this.last.setNext(null);
            this.currentNode = this.last;
            return;
        }
        if (this.currentNode != null) {
            this.currentNode.getBefore().setNext(this.currentNode.getNext());
            this.currentNode = this.currentNode.getBefore();
            if (this.currentNode.getBefore() == null) {
                this.first = this.currentNode;
            }
            if (this.currentNode.getNext() == null) {
                this.last = this.currentNode;
            }
        }

    }

    @Override
    public void close() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
