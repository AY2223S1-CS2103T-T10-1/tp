package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MODULES;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.model.Model;

/**
 * Navigates the user back to home page.
 */
public class HomeCommand extends Command {

    public static final String COMMAND_WORD = "home";

    public static final String MESSAGE_SUCCESS = "You're back at the home page!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setHomeStatus(true);
        updateFilteredList(model);
        return new CommandResult(MESSAGE_SUCCESS);
    }

    private void updateFilteredList(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        model.updateFilteredModuleList(PREDICATE_SHOW_ALL_MODULES);
    }
}
