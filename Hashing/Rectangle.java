import java.util.Objects;

/**
* comment this class completely, and in accordance with the style guide.
*/
public class Rectangle
{
	private int length;
	private int width;

	public Rectangle(int len, int w)
	{
		length = len;
		width = w;
	}

	public int getLength()
	{
		return length;
	}

	public int getWidth()
	{
		return width;
	}

	@Override
	public String toString()
	{
		return length + "x" + width;
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(length, width);
	}

	public boolean equals(Rectangle other)
	{
		return this.length == other.length && this.width == other.width;
	}
}