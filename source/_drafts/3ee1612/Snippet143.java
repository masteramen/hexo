import org.eclipse.swt.*;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.events.ShellListener;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.widgets.*;

public class Snippet143 {

    public static void main(String[] args) {
        Display display = new Display();
        final Shell shell = new Shell(display);
        shell.setText("Eclipse SDK");
        
        shell.setImage(new org.eclipse.swt.graphics.Image(Display.getCurrent(),
                "notify.jpg"));

        Image image = shell.getImage();
        final Tray tray = display.getSystemTray();
        if (tray == null) {
            System.out.println("The system tray is not available");
        } else {
            final TrayItem item = new TrayItem(tray, SWT.NONE);
            item.setVisible(false);
            item.setToolTipText("SWT TrayItem");
            item.addListener(SWT.Show, new Listener() {
                public void handleEvent(Event event) {
                    System.out.println("show");
                }
            });
            item.addListener(SWT.Hide, new Listener() {
                public void handleEvent(Event event) {
                    System.out.println("hide");
                }
            });
            item.addListener(SWT.Selection, new Listener() {
                public void handleEvent(Event event) {
                    System.out.println("selection");
                    toggleDisplay(shell, tray);
                }
            });
            item.addListener(SWT.DefaultSelection, new Listener() {
                public void handleEvent(Event event) {
                    System.out.println("default selection");

                }
            });
            final Menu menu = new Menu(shell, SWT.POP_UP);
            MenuItem mi = new MenuItem(menu, SWT.PUSH);
            mi.setText("Show " + shell.getText());
            mi.addListener(SWT.Selection, new Listener() {
                public void handleEvent(Event event) {
                    toggleDisplay(shell, tray);
                }
            });
            menu.setDefaultItem(mi);
            
            new MenuItem(menu, SWT.SEPARATOR);
            
            mi = new MenuItem(menu, SWT.PUSH);
            mi.setText("&About");
            
            mi.addListener(SWT.Selection, new Listener() {
                public void handleEvent(Event event) {

                }
            });
            
            item.addListener(SWT.MenuDetect, new Listener() {
                public void handleEvent(Event event) {
                    menu.setVisible(true);
                }
            });
            item.setImage(image);
        }

        shell.addShellListener(new ShellListener() {

            public void shellActivated(ShellEvent e) {
                // TODO Auto-generated method stub
                
            }

            public void shellClosed(ShellEvent e) {
                // TODO Auto-generated method stub
                
            }

            public void shellDeactivated(ShellEvent e) {
                // TODO Auto-generated method stub
                
            }

            public void shellDeiconified(ShellEvent e) {
                // TODO Auto-generated method stub
                
            }

            public void shellIconified(ShellEvent e) {
                toggleDisplay(shell, tray);
            }
            
        });
        
        shell.setBounds(50, 50, 300, 200);
        shell.setVisible(false);

        shell.open();
        shell.setVisible(false);
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }
        image.dispose();
        display.dispose();
    }
    
    /**
     * Toggle the display of current shell and tray icon.
     * 
     * @author BeanSoft(beansoft@126.com)
     * @param shell
     * @param tray
     */
    protected static void toggleDisplay(Shell shell, Tray tray) {
        try {
            shell.setVisible(!shell.isVisible());
            tray.getItem(0).setVisible(!shell.isVisible());
            // TODO Get focus???! Like QQ?
//            shell.setFocus();
//            shell.setActive();
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }
    }
}