import app
import unittest

class Flasktestcase(unittest.TestCase):
	def test_index(self):
		result = app.login_table("dharinath@virtusa.com")
		self.assertEqual(result,"ok")
if __name__ == '__main__':
	unittest.main()
