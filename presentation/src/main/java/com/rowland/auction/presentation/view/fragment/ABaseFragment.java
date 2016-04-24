package com.rowland.auction.presentation.view.fragment;

import android.support.v4.app.Fragment;
import android.widget.EditText;
import android.widget.Toast;

import com.rowland.auction.presentation.internal.di.HasComponent;

/**
 * Base {@link Fragment} class for every fragment in this application.
 */
public abstract class ABaseFragment extends Fragment {
    /**
     * Shows a {@link Toast} message.
     *
     * @param message An string representing a message to be shown.
     */
    protected void showToastMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Shows a {@link EditText} message.
     *
     * @param etToBeValidated An EditText whose data is to be validated.
     */
    private boolean isValidEditTextData(EditText etToBeValidated) {
        String etTxt = etToBeValidated.getText().toString().trim();
        if (etTxt == null) {
            if (etToBeValidated.getText().length() == 0) {
                etToBeValidated.setError("Required");
            }
            return false;
        }
        return true;
    }

    /**
     * Shows a {@link EditText} message.
     *
     * @param etToBeValidated An array of EditText whose data are to be validated.
     */
    protected boolean isValidEditTextData(EditText... etToBeValidated) {
        boolean isEtTextDataValid = true;
        for (int i = 0; i < etToBeValidated.length; i++) {
            EditText et = etToBeValidated[i];
            isEtTextDataValid = isValidEditTextData(et);
            if (!isEtTextDataValid) {
                return isEtTextDataValid;
            }
        }
        return isEtTextDataValid;
    }

    /**
     * Gets a component for dependency injection by its type.
     */
    @SuppressWarnings("unchecked")
    protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HasComponent<C>) getActivity()).getComponent());
    }
}
