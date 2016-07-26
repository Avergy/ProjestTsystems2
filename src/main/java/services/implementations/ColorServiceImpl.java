package services.implementations;

/**
 * @author Alina Golubcova
 * @version 1.0
 */

import dao.interfaces.ColorDao;
import entity.Color;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import services.interfaces.ColorService;

import java.util.List;
/**
 * Class for control entity color.
 */
@Service
@Transactional
public class ColorServiceImpl implements ColorService {

    @Autowired
    private ColorDao colorDao;

    /**
     * Create new Color in Database.
     *
     * @param color
     *      value
    */

    public void addNewColor(Color color){
        colorDao.create(color);
    }

    /**
     * Find Color by ColorNAme in Database
     * @param colorName
     *          String value name color
     * @return
     *      color
     */

    public Color findColorByName(String colorName) {
        return colorDao.findByColor(colorName);
    }

    /**
     * Load all color in Database
     * @return
     *      color list
     */

    @Override
    public List<Color> loadAllColors() {
        return colorDao.findAll();
    }

    /**
     * Update Color value in Database
     * @param color
     *      value
     */

    @Override
    public void updateColor(Color color) {
        colorDao.merge(color);

    }

}
