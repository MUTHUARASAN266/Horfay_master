import com.example.horfay123.model.LoginData
import com.example.horfay123.model.SignUpResponse

sealed class LoginResult{

    class Loading(val boolean: Boolean) : LoginResult()
    data class Success(val token: LoginData?) : LoginResult()
    data class Success1(val token: SignUpResponse) : LoginResult()
    data class Error(val message: String) : LoginResult()
}
sealed class DataStateResult<out R> {

    data class Success<out T>(val data: T) : DataStateResult<T>()

    //data class Error(val exception: Exception) : DataState<Nothing>()
    data class Error<out E>(val exception: Exception) : DataStateResult<E>()

    //object Loading : DataState<Nothing>()
    data class Loading<out B>(val loading: Boolean) : DataStateResult<B>()
}
sealed class SignInResult {
    data class Success(val response: SignUpResponse) : SignInResult()
    data class Error(val message: String) : SignInResult()
}