// Alon Livne (ID: 208688762)
package gameFlow;

/**
 * The HitNotifier interface represents an object that can notify HitListeners about hit events.
 */
public interface HitNotifier {

    /**
     * Add hl as a listener to hit events.
     *
     * @param hl The HitListener to be added.
     */
    void addHitListener(HitListener hl);

    /**
     * Remove hl from the list of listeners to hit events.
     *
     * @param hl The HitListener to be removed.
     */
    void removeHitListener(HitListener hl);
}