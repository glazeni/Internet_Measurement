
/* To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package internetmeasurement.android.TCPClient;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class RTOutputStream extends FilterOutputStream {


    public RTOutputStream(OutputStream out) {
        super(out);
    }

    @Override
    public void write(int b) throws IOException {
        super.write(b);
        super.flush();
    }

    @Override
    public void write(byte data[]) throws IOException {
        super.write(data);
        super.flush();  
    }

    @Override
    public void write(byte data[], int off, int len) throws IOException {
        super.write(data, off, len);
        super.flush();
    }
    
}
