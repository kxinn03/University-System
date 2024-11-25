/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package adt;

/**
 *
 * @author Wong Xin Yee, Ting Xin Yi, Lee Kar Xin
 */
public interface SortedLinkedListInterface<T extends Comparable<T>>  {
    public boolean add(T newEntry);
 
      public boolean remove(T anEntry);
      
      public boolean removeAt(int positionToRemove);

      public boolean contains(T anEntry);
      
      public int getNumberOfEntries();

      public boolean isEmpty();
      
      public void clear();
      
      public T getEntry(int givenPosition);
      
      public boolean amend(T existingEntry, T newEntry);

      public T search(T anEntry);
      
}
