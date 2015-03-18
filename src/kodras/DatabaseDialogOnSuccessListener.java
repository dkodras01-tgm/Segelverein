package kodras;

public interface DatabaseDialogOnSuccessListener {
	public abstract void onSuccess(String host, String datenbank, String benutzer, String passwort);
}