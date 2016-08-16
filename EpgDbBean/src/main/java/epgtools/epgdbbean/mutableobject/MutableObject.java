package MutableObject;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Map;

/**
 *  * リスナを登録しておくと、このクラスの保持している情報が変更されたときに通知してくれる。
 *
 * @author dosdiaopfhj
 */
public abstract class MutableObject {

    /**
     * プロパティの変更通知をサポートするオブジェクト
     *
     * @see java.beans.PropertyChangeSupport#firePropertyChange
     *
     * 変更メソッドに追加する内容: propertyChangeSupport.firePropertyChange("property",
     * oldValue, newValue);
     */
    protected final transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    /**
     */
    protected MutableObject() {
    }

    /**
     * リスナの追加
     *
     * @param listener 追加するリスナ
     */
    public void addPropertyChangeListener(final PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    /**
     * リスナの追加(特定のプロパティ名を指定可)
     *
     * @param property リスナが監視するプロパティ名
     * @param listener 追加するリスナ
     */
    public void addPropertyChangeListener(final String property,
            final PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(property, listener);
    }

    /**
     * リスナの削除
     *
     * @param listener 削除するリスナ
     */
    public void removePropertyChangeListener(final PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    /**
     * リスナの削除(特定のプロパティ名を指定可)
     *
     * @param property 削除するリスナが監視していたプロパティ名
     * @param listener 削除するリスナ
     */
    public void removePropertyChangeListener(final String property,
            final PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(property, listener);
    }

    /**
     * 検査対象のフィールドがnullの場合、例外を発生させる。
     *
     * @param target 検査対象。
     */
    protected synchronized final void checkNull(Object target) {
        if (target == null) {
            throw new NullPointerException("nullのセットは禁止です。");
        }
    }

    /**
     * @return @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public abstract boolean equals(Object obj);

    /**
     * @return @see java.lang.Object#hashCode()
     */
    @Override
    public abstract int hashCode();

    /**
     * テキストに変換可能なものを全てダンプする。
     *
     * @return このオブジェクトが保存している内容えお文字列に変換したもの。
     */
    public String toString() {
        try {
            final BeanInfo info = Introspector.getBeanInfo(this.getClass(), Object.class);
            final PropertyDescriptor[] props = info.getPropertyDescriptors();
            final StringBuffer buf = new StringBuffer(500);
            Object value = null;
            buf.append(getClass().getName());
            buf.append("@");  //$NON-NLS-1$
            buf.append(hashCode());
            buf.append("={");  //$NON-NLS-1$
            for (int idx = 0; idx < props.length; idx++) {
                if (idx != 0) {
                    buf.append(", ");  //$NON-NLS-1$
                }
                buf.append(props[idx].getName());
                buf.append("=");  //$NON-NLS-1$
                if (props[idx].getReadMethod() != null) {
                    value = props[idx].getReadMethod()
                            .invoke(this, new Object[]{});
                    if (value instanceof MutableObject) {
                        buf.append("@");  //$NON-NLS-1$
                        buf.append(value.hashCode());
                    } else if (value instanceof Collection) {
                        buf.append("{");  //$NON-NLS-1$
                        for (Object element : ((Collection<?>) value)) {
                            if (element instanceof MutableObject) {
                                buf.append("@");  //$NON-NLS-1$
                                buf.append(element.hashCode());
                            } else {
                                buf.append(element.toString());
                            }
                        }
                        buf.append("}");  //$NON-NLS-1$
                    } else if (value instanceof Map) {
                        buf.append("{");  //$NON-NLS-1$
                        Map<?, ?> map = (Map) value;
                        for (Object key : map.keySet()) {
                            Object element = map.get(key);
                            buf.append(key.toString()).append("=");
                            if (element instanceof MutableObject) {
                                buf.append("@");  //$NON-NLS-1$
                                buf.append(element.hashCode());
                            } else {
                                buf.append(element.toString());
                            }
                        }
                        buf.append("}");  //$NON-NLS-1$
                    } else {
                        if (value != null) {
                            buf.append(value);
                        } else {
                            buf.append("null");
                        }
                    }
                }
            }
            buf.append("}");  //$NON-NLS-1$
            return buf.toString();
        } catch (IntrospectionException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            throw new RuntimeException(ex);
        }
    }

}
