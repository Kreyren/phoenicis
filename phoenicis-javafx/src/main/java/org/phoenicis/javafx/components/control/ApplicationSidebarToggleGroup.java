package org.phoenicis.javafx.components.control;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import org.phoenicis.javafx.components.skin.ApplicationSidebarToggleGroupSkin;
import org.phoenicis.javafx.views.mainwindow.apps.ApplicationsSidebar;
import org.phoenicis.repository.dto.CategoryDTO;

import java.util.function.Consumer;

/**
 * A toggle group component used inside the {@link ApplicationsSidebar}
 */
public class ApplicationSidebarToggleGroup
        extends SidebarToggleGroupBase<CategoryDTO, ApplicationSidebarToggleGroup, ApplicationSidebarToggleGroupSkin> {
    /**
     * A consumer, which is called when the "all" categories button has been selected
     */
    private final ObjectProperty<Runnable> onAllCategorySelection;

    /**
     * A consumer, which is called when a category has been selected
     */
    private final ObjectProperty<Consumer<CategoryDTO>> onCategorySelection;

    /**
     * Constructor
     *
     * @param title The title of the application sidebar toggle group
     */
    public ApplicationSidebarToggleGroup(String title) {
        super(title);

        this.onAllCategorySelection = new SimpleObjectProperty<>();
        this.onCategorySelection = new SimpleObjectProperty<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ApplicationSidebarToggleGroupSkin createSkin() {
        return new ApplicationSidebarToggleGroupSkin(this);
    }

    public Runnable getOnAllCategorySelection() {
        return onAllCategorySelection.get();
    }

    public ObjectProperty<Runnable> onAllCategorySelectionProperty() {
        return onAllCategorySelection;
    }

    public void setOnAllCategorySelection(Runnable onAllCategorySelection) {
        this.onAllCategorySelection.set(onAllCategorySelection);
    }

    public Consumer<CategoryDTO> getOnCategorySelection() {
        return onCategorySelection.get();
    }

    public ObjectProperty<Consumer<CategoryDTO>> onCategorySelectionProperty() {
        return onCategorySelection;
    }

    public void setOnCategorySelection(Consumer<CategoryDTO> onCategorySelection) {
        this.onCategorySelection.set(onCategorySelection);
    }
}