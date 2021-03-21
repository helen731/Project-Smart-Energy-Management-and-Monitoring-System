import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Can save multiple the value of the map
 * @author wangxudong
 */
public interface MultiValueMap<K, V> {

    /**
     * add Key-Value
     *
     * @param key   key.
     * @param value value.
     */
    void add(K key, V value);

    /**
     * add Key-List 
     * @param key key
     * @param values values
     */
    void add(K key, List<V> values);

    /**
     * set a Key-Value
     *
     * @param key   key.
     * @param value values.
     */
    void set(K key, V value);

    /**
     * set a Key-List. If this key exists, it is replaced, if it does not exist, it is added.
     * @param key    key.
     * @param values values.
     * @see #set(Object, Object)
     */
    void set(K key, List<V> values);

    /**
     * replace all Key-List
     *
     * @param values values.
     */
    void set(Map<K, List<V>> values);

    /**
     * remove Key
     *
     * @param key key.
     * @return value.
     */
    List<V> remove(K key);

    /**
     * Remove all key-value.
     */
    void clear();

    /**
     * get the list of Key
     * @return Set.
     */
    Set<K> keySet();

    /**
     * Get the set of all values
     *
     * @return List.
     */
    List<V> values();

    /**
     * Get the value of a key
     *
     * @param key   key.
     * @param index index value.
     * @return The value.
     */
    V getValue(K key, int index);

    /**
     * Change a value of key
     * @param index index value
     * @param key   key.
     * @param value The value
     * @return NULL
     */
    V setValue(K key, int index, V value);

    /**
     * Get all values of a key
     *
     * @param key key.
     * @return values.
     */
    List<V> getValues(K key);

    /**
     * Get the size of the map
     *
     * @return size.
     */
    int size();

    /**
     * Determine if MultiValueMap is null.
     * @return True: empty, false: not empty.
     */
    boolean isEmpty();

    /**
     * Determine if the MultiValueMap contains a Key.
     *
     * @param key key.
     * @return True: contain, false: none.
     */
    boolean containsKey(K key);

}
