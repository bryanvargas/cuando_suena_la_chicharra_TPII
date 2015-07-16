package vistas;

import java.awt.Component;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;

import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.Icon;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

public class MiTabbledPane extends JTabbedPane {

	private static final long serialVersionUID = 8750735317156728355L;	
	protected int dragTabIndex = -1;
    protected boolean hasGhost = true;
    protected boolean isPaintScrollArea = true;

    protected Rectangle rBackward = new Rectangle();
    protected Rectangle rForward  = new Rectangle();
    
    public void autoScrollTest(Point glassPt) {
        rBackward = SwingUtilities.convertRectangle(getParent(), rBackward, null);
        rForward  = SwingUtilities.convertRectangle(getParent(), rForward,null);
        if (rBackward.contains(glassPt)) {
            clickArrowButton("scrollTabsBackwardAction");
        } else if (rForward.contains(glassPt)) {
            clickArrowButton("scrollTabsForwardAction");
        }
    }
    private void clickArrowButton(String actionKey) {
        ActionMap map = getActionMap();
        if (map != null) {
            Action action = map.get(actionKey);
            if (action != null && action.isEnabled()) {
                action.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null, 0, 0));
            }
        }
    }   
    
    protected void convertTab(int prev, int next) {
        if (next < 0 || prev == next) {
            return;
        }
        Component cmp = getComponentAt(prev);
        Component tab = getTabComponentAt(prev);
        String str    = getTitleAt(prev);
        Icon icon     = getIconAt(prev);
        String tip    = getToolTipTextAt(prev);
        boolean flg   = isEnabledAt(prev);
        int tgtindex  = prev > next ? next : next - 1;
        remove(prev);
        insertTab(str, icon, cmp, tip, tgtindex);
        setEnabledAt(tgtindex, flg);
        if (flg) {
            setSelectedIndex(tgtindex);
        }
        setTabComponentAt(tgtindex, tab);
    }
}
