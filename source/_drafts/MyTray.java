import org.eclipse.swt.SWT;
  import org.eclipse.swt.events.ShellListener;
  import org.eclipse.swt.graphics.Image;
  import org.eclipse.swt.internal.win32.OS;
  import org.eclipse.swt.widgets.Display;
 import org.eclipse.swt.widgets.Event;
  import org.eclipse.swt.widgets.Listener;
  import org.eclipse.swt.widgets.Menu;
 import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tray;
import org.eclipse.swt.widgets.TrayItem;

public class MyTray {
    public static void main(String[] args) {
        Display display = new Display();
        Shell shell = new Shell(display);
        // 下面两句的效果是：在任务栏不显示
        // 感谢CSDN会员：GhostValley(鬼谷)
        final int hWnd = (int) shell.handle;
        OS.SetWindowLong(hWnd, OS.GWL_EXSTYLE, OS.WS_EX_CAPTIONOKBTN);
        Image image = new Image(display, 16, 16);
        final Tray tray = display.getSystemTray();
        if (tray == null) {
            System.out.println("The system tray is not available");
        } else {
            final TrayItem item = new TrayItem(tray, SWT.NONE);
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
                }
            });
            item.addListener(SWT.DefaultSelection, new Listener() {
                public void handleEvent(Event event) {
                    System.out.println("default selection");
                    // show main
                    Shell s = event.display.getShells()[0];
                    s.setVisible(true);
                    s.setMinimized(false);
                }
            });
            final Menu menu = new Menu(shell, SWT.POP_UP);
            for (int i = 0; i < 8; i++) {
                MenuItem mi = new MenuItem(menu, SWT.PUSH);
                mi.setText("Item" + i);
            }
            item.addListener(SWT.MenuDetect, new Listener() {
                public void handleEvent(Event event) {
                    menu.setVisible(true);
                }
            });
            item.setImage(image);
        }
        shell.addShellListener(new ShellListener() {
            public void shellDeactivated(org.eclipse.swt.events.ShellEvent e) {
            }

            public void shellActivated(org.eclipse.swt.events.ShellEvent e) {
            }

            public void shellClosed(org.eclipse.swt.events.ShellEvent e) {
            }

            public void shellDeiconified(org.eclipse.swt.events.ShellEvent e) {
            }

            public void shellIconified(org.eclipse.swt.events.ShellEvent e) {
                // 最小化时不显示在任务栏
                // 感谢CSDN会员：pdvv(我爱花猫)
                ((Shell) e.getSource()).setVisible(false);
            }
        });
        shell.setBounds(50, 50, 300, 200);
        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }
        image.dispose();
        display.dispose();
    }
}