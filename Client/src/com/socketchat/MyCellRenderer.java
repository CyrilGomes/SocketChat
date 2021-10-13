package com.socketchat;

import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

/*
    From StackOverflow: https://stackoverflow.com/questions/7306295/swing-jlist-with-multiline-text-and-dynamic-height
*/
public class MyCellRenderer extends DefaultListCellRenderer
    {
        final JPanel p = new JPanel(new BorderLayout());
        final JPanel IconPanel = new JPanel(new BorderLayout());
        final JLabel l = new JLabel("icon"); //<-- this will be an icon instead of a text
        final JLabel lt = new JLabel();
        String pre = "<html><body style='width: 200px;'>";

        MyCellRenderer() {
            //icon
            IconPanel.add(l, BorderLayout.NORTH);
            p.add(IconPanel, BorderLayout.WEST);

            p.add(lt, BorderLayout.CENTER);
            //text
        }

        @Override
        public Component getListCellRendererComponent(final JList list, final Object value, final int index, final boolean isSelected, final boolean hasFocus)
        {
            final String text = (String) value;
            lt.setText(pre + text);

            return p;
        }
    }