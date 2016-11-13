import java.util.Date;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.FixedValue;

public class Main
{
    public static void main(String[] args)
    {
        Date start = new Date();
        int numberOfInstances = 1000000000;
        for (int i = 0; i < numberOfInstances; i++)
        {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(SampleImpl.class);
            enhancer.setCallback(new FixedValue()
            {
                public Object loadObject() throws Exception
                {
                    return "Hello cglib!";
                }
            });
            SampleImpl proxy = (SampleImpl) enhancer.create();
            proxy = null;
        }
        Date stop = new Date();
        System.out.println(numberOfInstances + " created in " + (stop.getTime() - start.getTime()));
    }
}
