package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.model.Model;

/**
 * Navigates the user back to homepage
 */
public class HomeCommand extends Command {

    public static final String COMMAND_WORD = "home";

    public static final String MESSAGE_SUCCESS = "You're back at the home page!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        // Should be
        // updateFilteredContactList(PREDICATE_SHOW_ALL_PERSONS)
        // updateFilteredModuleList(PREDICATE_SHOW_ALL_PERSONS)
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
