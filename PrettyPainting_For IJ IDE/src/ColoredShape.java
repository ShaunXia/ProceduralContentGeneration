import java.awt.Color;
import java.awt.Shape;
import java.io.Serializable;

public class ColoredShape implements Serializable
{

	private Color color;
	public void setColor(Color color)
	{
		this.color = color;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((shape == null) ? 0 : shape.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ColoredShape other = (ColoredShape) obj;
		if (color == null)
		{
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (shape == null)
		{
			if (other.shape != null)
				return false;
		} else if (!shape.equals(other.shape))
			return false;
		return true;
	}

	private Shape shape;

	public ColoredShape(Color color, Shape shape)
	{
		super();
		this.color = color;
		this.shape = shape;
	}

	public ColoredShape(ColoredShape guyToCopy)
	{
		this.color = guyToCopy.getColor();
		this.shape = guyToCopy.shape;
	}
	public Color getColor()
	{
		return color;
	}

	public Shape getShape()
	{
		return shape;
	}
	

}
