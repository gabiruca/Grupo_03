/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TDAs;

import java.util.Iterator;

/**
 *
 * @author Gabriela
 */
public class LinkedList <E> implements List<E> {
    private Node<E> first, last;
    private int efectivo;
    
    public LinkedList(){
        first = last = null;
        efectivo = 0;
    }
    
    @Override
    public boolean addFirst(E element) {
        Node<E> nodo = new Node<>(element);
        if(element == null)
            return false;
        else if(isEmpty())
            first = last = nodo;
        else{
            nodo.setNext(first);
            first = nodo;
        }
        efectivo++;
        return true;
    }

    @Override
    public boolean addLast(E element) {
        Node<E> nodo = new Node<>(element);
        if(element == null)
            return false;
        else if(isEmpty())
            first = last = nodo;
        else{
            last.setNext(nodo);
            last = nodo;
        }
        
        efectivo ++;
        return true;
    }

    @Override
    public E getFirst() {
        return first.getContent();
    }

    @Override
    public E getLast() {
        return last.getContent();
    }

    @Override
    public boolean removeFirst() {
        if(isEmpty())
            return false;
        else if(first == last)
            first = last = null;
        else{
            Node<E> tmp = first;
            first = first .getNext();
            tmp.setNext(null);
        }
        efectivo--;
        return true;
    }
    
    @Override
    public boolean removeLast(){
        if(this.isEmpty())
            return false;
        else if(first == last)
            first = last= null;
        else{
            Node<E> nodo = first;
            while(nodo.getNext() != last){
                nodo = nodo.getNext();
            }
            
            last = nodo;
            last.setNext(null);
        }
        return true;
    }
    
    private Node<E> getPrevious(Node<E> nodo){
        if(nodo == first)
            return null;
        for(Node<E> i = first; i != null; i = i.getNext()){
            if(i.getNext() == nodo)
                return i;
        }
        return null;
    }
    
    @Override
    public boolean isEmpty() {
        return (first == null && last == null);
    }

    @Override
    public boolean contains(E element) {
        if(element == null || isEmpty()){
            return false;
        }
        for(Node<E> i = first; i != null; i = i.getNext()){
            if(i.getContent().equals(element))
                return true;
        }
        return false;
    }

    @Override
    public boolean insert(int index, E element) {
        if(element == null || index < 0 || index >= efectivo) {
            return false;
        }else if(index == 0){
            addFirst(element);
            efectivo ++;
            return true;
        }else if( index == efectivo-1){
            addLast(element);
            efectivo ++;
            return true;
        }
        
        int i = 0;
        Node<E> nodo = new Node<>(element);
        for(Node<E> j = first; j != null; j = j.getNext()){
            if(index -1  == i){
                nodo.setNext(j.getNext());
                j.setNext(nodo);
                efectivo++;
                return true;
            }
            i++;
        }
        return false;
    }

    @Override
    public E get(int index) {
        if(index < 0 || index >= efectivo)
            return null;
        int j = 0;
        for(Node<E> i = first; i != null; i = i.getNext()){
            if(j == index)
                return i.getContent();
            j++;
        }
        return null;
    }

    @Override
    public int indexOf(E element) {
        if(element == null)
            return -1;
        
        int j = 0;
        for(Node<E> i = first; i != null; i = i.getNext()){
            if(i.getContent().equals(element))
                return j;
            j++;
        }
        return -1;
    }

    @Override
    public E remove(int index) {
        if(index < 0 || index >= efectivo){
            return null;
        }else if(index == 0){
            E tmp = getFirst();
            removeFirst();
            return tmp;
       }else if( index == efectivo - 1){
           E tmp = getLast();
           removeLast();
           return tmp;
        }
        
        Node<E> j = first.getNext();
        for (int i = 1; i != index-1; i++){
            j = j.getNext();
        }
        
        Node<E> tmp = j.getNext();
        j.setNext(tmp.getNext());
        tmp.setNext(null);
        efectivo--;
        return tmp.getContent();
    }

    @Override
    public boolean remove(E element) {
        if(element == null) {
            return false;
        }else if(first.getContent().equals(element)){
            removeFirst();
            return true;
        }else if(last.getContent().equals(element)){
           removeLast();
           return true;
        }
        
        for(Node<E> i = first; i != null; i = i.getNext()){
            if(i.getContent().equals(element)){
                Node<E> previo = getPrevious(i);
                previo.setNext(i.getNext());
                i.setNext(null);
                efectivo--;
                return true;
            }
        }
        return false;
    }

    @Override
    public E set(int index, E element) {
        if (element == null || index < 0 || index >= efectivo){
            return null;
        }
        
        int j = 0;
        for(Node<E> i = first; i != null; i = i.getNext()){
            if(j == index){
                E tmp = i.getContent();
                i.setContent(element);
                return tmp;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return efectivo;
    }
    
    @Override
    public boolean equals(Object o){
        if(o == null || ! (o instanceof LinkedList))
            return false;
        LinkedList<E> lista = (LinkedList<E>) o;
        
        if(efectivo != lista.efectivo)
            return false;
        
        Node<E> nodo = lista.first;
        
        for(Node<E> i = first; i !=null; i = i.getNext()){
            if(!nodo.getContent().equals(i.getContent()))
                return false;
            
            nodo = nodo.getNext();
        }
        return true;
    }
    
    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();
        if(isEmpty())
            return "[]";
        s.append("[");
        
        for(Node<E> i = first; i != null; i = i.getNext()){
            if(i != last)
                s.append(i.getContent() + ",");
            else
                s.append(i.getContent() + "]");
        }
        return s.toString();
    }
    
    
    public Iterator<E> iterator() {
        Iterator<E> it = new Iterator<E>(){
            private Node<E> p = first;
            
            @Override
            public boolean hasNext() {
                return p!= null;
            }

            @Override
            public E next() {
                E tmp = p.getContent();
                p = p.getNext();
                return tmp;
            }
        };
        
        return it;
    }
}
