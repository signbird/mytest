package org.bqftest.jvm.classloading;

public class Test
{

	public static void main(String[] args)
	{
		int a, b, c, d, e, f, g, h;
		
		for (a = 0; a <= 100; a++)
		{
			b = 13 - a;
			for (f = 0; f <= 100; f++)
			{
				c = (4 - a) * f;
				for (e = 0; e <= 100; e++)
				{
					h = 5 - e;
					g = 4 + h - f;
					if (e == 0 && g == 0)
						continue;
					d = e == 0 ? (b - 4) / g : (c - 4) / e;
					
					if ((e == 0 ? true : (c - 4) % e == 0) 
							&& (g == 0 ? true : (b - 4) % g == 0) 
							&& (b - d * g == 4)
					        && (c - d * e == 4)
					        && f != 0)
					{
						System.out.print("a=" + a);
						System.out.print(",b=" + b);
						System.out.print(",c=" + c);
						System.out.print(",d=" + d);
						System.out.print(",e=" + e);
						System.out.print(",f=" + f);
						System.out.print(",g=" + g);
						System.out.print(",h=" + h);
						System.out.println();
					}
				}
			}
		}
	}
}
