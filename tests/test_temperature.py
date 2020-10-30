import os
import subprocess
import pytest
import sys

TESTS_CASES = [
    (b"F 100", "37.77777777777778", "100.0", "310.92777777777775"),
]


# We need this tweak for GitHub Actions, I wonder if we also need this on a Windows machine.
print(sys.platform)
NL = "\n"
if sys.platform == 'win32':
    print("setting newline")
    NL = "\r\n"

@pytest.mark.parametrize("stdn,celsius,fahrenheit,kelvin", TESTS_CASES)
def test_temperature(stdn, celsius, fahrenheit, kelvin):
    assert os.system("javac Temperature.java") == 0
    proc = subprocess.Popen(["java", "Temperature"],
                            stdin=subprocess.PIPE,
                            stdout=subprocess.PIPE,
                            stderr=subprocess.PIPE)
    out, err = proc.communicate(input=stdn)
    x = f'Please enter a temperature unit identifying character and a value:{NL}{celsius} C{NL}{fahrenheit} F{NL}{kelvin} K{NL}'
    assert out == x.encode()
    assert err == b''
