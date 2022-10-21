package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_LINK;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

import seedu.address.logic.commands.DeleteLinkCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.link.Link;
import seedu.address.model.module.ModuleCode;

/**
 * Parses input arguments and creates a new {@code DeleteLinkCommand} object
 */
public class DeleteLinkCommandParser implements Parser<DeleteLinkCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code DeleteLinkCommand}
     * and returns an {@code DeleteLinkCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteLinkCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_MODULE_CODE,
                PREFIX_MODULE_LINK);

        String moduleCodeStringToEdit = argMultimap.getValue(PREFIX_MODULE_CODE)
                .orElseThrow(() -> new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                                DeleteLinkCommand.MESSAGE_USAGE)));

        //Throws ParseException if module code or link/s is invalid
        ModuleCode moduleCodeToEdit = ParserUtil.parseModuleCode(moduleCodeStringToEdit);
        Optional<Set<Link>> links = parseLinksToDelete(argMultimap.getAllValues(PREFIX_MODULE_LINK));

        return new DeleteLinkCommand(moduleCodeToEdit,
                links.orElseThrow(() -> new ParseException(DeleteLinkCommand.MESSAGE_NOT_EDITED)));
    }

    /**
     * Parses {@code Collection<String> links} into a {@code Set<Link>} if {@code links} is non-empty.
     * If {@code links} contain only one element which is an empty string, {@code links} is treated as empty.
     */
    private Optional<Set<Link>> parseLinksToDelete(Collection<String> links) throws ParseException {
        assert links != null;
        boolean isLinksEmpty = links.isEmpty();
        boolean hasOnlyOneEmptyLink = links.size() == 1 && links.contains("");
        if (isLinksEmpty || hasOnlyOneEmptyLink) {
            return Optional.empty();
        }
        return Optional.of(ParserUtil.parseLinks(links));
    }
}
