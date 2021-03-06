package info.guardianproject.justpayphone.app.screens.wizard;
import info.guardianproject.justpayphone.utils.Constants.WizardActivityListener;
import info.guardianproject.justpayphone.utils.UIHelpers;
import info.guardianproject.justpayphone.R;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class WizardLawyerInformation extends WizardFragmentBase implements OnClickListener
{
	private Button commit;
	private EditText number;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
	}

	@Override
	protected int getLayout() {
		return R.layout.fragment_wizard_lawyer_information;
	}
	
	@Override
	public View onCreateView(LayoutInflater li, ViewGroup container, Bundle savedInstanceState)
	{
		super.onCreateView(li, container, savedInstanceState);

		number = (EditText) rootView.findViewById(R.id.lawyer_number);
		number.addTextChangedListener(readNumber);
		
		commit = (Button) rootView.findViewById(R.id.wizard_commit);
		commit.setEnabled(false);
		commit.setOnClickListener(this);
		return rootView;
	}

	@Override
	public void onClick(View v)
	{
		if (v == commit)
		{
			if (isEverythingOk())
			{
				UIHelpers.hideSoftKeyboard(a);
				if (a instanceof WizardActivityListener)
				{
					((WizardActivityListener) a).onLawyerInfoSet(number.getText().toString());
				}
			}
		}
	}
	
	private boolean isEverythingOk()
	{
		if(number.getText().length() >= 2) {
			return true;
		}
		return false;
	}
	
	private void enableDisableCommit()
	{
		commit.setEnabled(isEverythingOk());
	}
	
	TextWatcher readNumber = new TextWatcher() {

		@Override
		public void afterTextChanged(Editable s) {
			enableDisableCommit();
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {

		}
	};
}
