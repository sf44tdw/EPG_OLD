/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MutableObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author dosdiaopfhj
 */
public class MutableObjectImpl extends MutableObject {

    /**
     * StringProp
     */
    private String stringProp;

    /**
     * IngegerProp
     */
    private Integer ingtgerProp;

    /**
     * CollectionProp
     *
     */
    private Collection<String> collectionProp;

    /**
     * MapProp
     *
     */
    private Map<Integer, String> mapProp;

    /**
     * StringProp取得
     *
     * @return
     */
    public String getStringProp() {
        return stringProp;
    }

    /**
     * StringProp設定
     *
     * @param stringProp
     */
    public void setStringProp(String stringProp) {
        String oldValue = this.getStringProp();
        this.stringProp = stringProp;
        this.propertyChangeSupport.firePropertyChange("stringProp", oldValue, stringProp);
    }

    /**
     * IngegerProp取得
     *
     * @return
     */
    public Integer getIntegerProp() {
        return ingtgerProp;
    }

    /**
     * IngegerProp設定
     *
     * @param integerProp
     */
    public void setIntegerProp(Integer integerProp) {
        Integer oldValue = this.getIntegerProp();
        this.ingtgerProp = integerProp;
        this.propertyChangeSupport.firePropertyChange("integerProp", oldValue, integerProp);
    }

    public Collection<String> getCollectionProp() {
        if (this.collectionProp != null) {
            return Collections.unmodifiableCollection(collectionProp);
        } else {
            return null;
        }
    }

    public void setCollectionProp(final Collection<String> collectionProp) {
        this.collectionProp = Collections.synchronizedList(new ArrayList<String>());
        String oldVal;
        String newVal;
        if (this.collectionProp != null) {
            oldVal = this.collectionProp.toString();
        } else {
            oldVal = "NULL";
        }
        if (collectionProp != null) {
            this.collectionProp.addAll(collectionProp);
            newVal = this.collectionProp.toString();
        } else {
            this.collectionProp = null;
            newVal = "NULL";
        }
        this.propertyChangeSupport.firePropertyChange("collectionProp", oldVal, newVal);
    }

    public Map<Integer, String> getMapProp() {
        if (this.mapProp != null) {
            return Collections.unmodifiableMap(mapProp);
        } else {
            return null;
        }
    }

    public void setMapProp(final Map<Integer, String> mapProp) {
        this.mapProp = Collections.synchronizedMap(new HashMap<Integer, String>());
        String oldVal;
        String newVal;
        if (this.mapProp != null) {
            oldVal = this.mapProp.toString();
        } else {
            oldVal = "NULL";
        }
        if (mapProp != null) {
            this.mapProp.putAll(mapProp);
            newVal = this.mapProp.toString();
        } else {
            this.mapProp = null;
            newVal = "NULL";
        }
        this.propertyChangeSupport.firePropertyChange("mapProp", oldVal, newVal);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj instanceof MutableObjectImpl) {
            MutableObjectImpl o = (MutableObjectImpl) obj;
            if (!(Objects.equals(this.getIntegerProp(), o.getIntegerProp()))) {
                return false;
            }
            if (!(this.getStringProp() == null ? o.getStringProp() == null : this.getStringProp().equals(o.getStringProp()))) {
                return false;
            }

            if (this.collectionProp != null && o.getCollectionProp() != null) {

                if (!(this.collectionProp.size() == o.getCollectionProp().size())) {
                    return false;
                }

                if (!(Arrays.deepEquals(this.collectionProp.toArray(), o.getCollectionProp().toArray()))) {
                    return false;
                }

            } else if (!(this.collectionProp == null && o.getCollectionProp() == null)) {
                return false;
            }

            if (this.mapProp != null && o.getMapProp() != null) {

                if (!(this.mapProp.size() == o.getMapProp().size())) {
                    return false;
                }

                for (Map.Entry<Integer, String> entry : this.mapProp.entrySet()) {
                    // キーを取得
                    Integer key = entry.getKey();
                    // 値を取得
                    String val = entry.getValue();
                    if (!(o.getMapProp().get(key) == null ? val == null : o.getMapProp().get(key).equals(val))) {
                        return false;
                    }
                }

            } else if (!(this.mapProp == null && o.getMapProp() == null)) {
                return false;
            }

            return true;

        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.stringProp);
        hash = 23 * hash + Objects.hashCode(this.ingtgerProp);
        hash = 23 * hash + Objects.hashCode(this.collectionProp);
        hash = 23 * hash + Objects.hashCode(this.mapProp);
        return hash;
    }

}
