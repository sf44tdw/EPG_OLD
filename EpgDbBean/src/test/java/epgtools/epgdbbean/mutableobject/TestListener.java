/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MutableObject;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 *
 * @author dosdiaopfhj
 */
public class TestListener implements PropertyChangeListener {

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.printf(
                "source:%s, propertyName:%s, oldValue:%s, newValue:%s\n",
                evt.getSource(),
                evt.getPropertyName(),
                evt.getOldValue(),
                evt.getNewValue());
    }

}
