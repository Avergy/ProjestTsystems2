package services.interfaces;

/**
 * @author Alina Golubcova
 * @version 1.0
 */

import entity.Color;

import java.util.List;


public interface ColorService {

    void addNewColor(Color color);

    Color findColorByName(String color);

    List<Color> loadAllColors();

    void updateColor(Color color);

}
