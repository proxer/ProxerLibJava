package me.proxer.library.api;

import com.squareup.moshi.JsonDataException;
import me.proxer.library.entity.notifications.NotificationInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

/**
 * @author Ruben Gees
 */
class NotificationAdapterTest {

    private NotificationInfoAdapter adapter;

    @BeforeEach
    void setUp() {
        adapter = new NotificationInfoAdapter();
    }

    @Test
    void testFromJson() {
        assertThat(adapter.fromJson(new int[]{0, 0, 1, 2, 3, 4}))
                .isEqualTo(new NotificationInfo(1, 2, 3, 4));
    }

    @Test
    void testFromJsonInvalidSizeTooSmall() {
        assertThatThrownBy(() -> adapter.fromJson(new int[]{1, 2, 3, 4, 5}))
                .isInstanceOf(JsonDataException.class);
    }

    @Test
    void testFromJsonInvalidSizeTooLarge() {
        assertThatThrownBy(() -> adapter.fromJson(new int[]{1, 2, 3, 4, 5, 6, 7}))
                .isInstanceOf(JsonDataException.class);
    }
}
