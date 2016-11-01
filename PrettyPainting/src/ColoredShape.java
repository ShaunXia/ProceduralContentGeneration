import java.awt.Color;
import java.awt.Shape;

public class ColoredShape
{

	private Color color;
	private Shape shape;

	public ColoredShape(Color color, Shape shape)
	{
		super();
		this.color = color;
		this.shape = shape;
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
