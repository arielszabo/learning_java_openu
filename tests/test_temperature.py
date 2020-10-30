import os
import subprocess
import pytest

TESTS_CASES = [
    (b"F 100", "37.77777777777778", "100.0", "310.92777777777775"),
]


@pytest.mark.parametrize("stdn,celsius,fahrenheit,kelvin", TESTS_CASES)
def test_temperature(stdn, celsius, fahrenheit, kelvin):
    assert os.system("javac Temperature.java") == 0
    proc = subprocess.Popen(["java", "Temperature"],
                            stdin=subprocess.PIPE,
                            stdout=subprocess.PIPE,
                            stderr=subprocess.PIPE)
    out, err = proc.communicate(input=stdn)
    x = f'Please enter a temperature unit identifying character and a value:\n{celsius} C\n{fahrenheit} F\n{kelvin} K\n'
    assert out == x.encode()
    assert err == b''
