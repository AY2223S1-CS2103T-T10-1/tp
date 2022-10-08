package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_CS_MODULE_CODE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CS_MODULE_TITLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MA_MODULE_CODE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MA_MODULE_TITLE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.module.Module;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalModules {

    public static final Module CS2106 = new ModuleBuilder().withModuleCode(VALID_CS_MODULE_CODE)
            .withModuleTitle(VALID_CS_MODULE_TITLE).build();
    public static final Module MA2001 = new ModuleBuilder().withModuleCode(VALID_MA_MODULE_CODE)
            .withModuleTitle(VALID_MA_MODULE_TITLE).build();

    private TypicalModules() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Module module : getTypicalModules()) {
            ab.addModule(module);
        }
        return ab;
    }

    public static List<Module> getTypicalModules() {
        return new ArrayList<>(Arrays.asList(CS2106, MA2001));
    }
}
