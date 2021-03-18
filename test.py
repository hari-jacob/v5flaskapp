import app as appl
from app import app
import unittest

app.testing = True
#client = app.test_client()

class Flask_func_testcase(unittest.TestCase):
	def test_logintable(self):
		result = appl.login_table("dharinath@virtusa.com")
		self.assertEqual(result,"ok")

	def test_sendmail(self):
		result = appl.send_mail("dharinath@virtusa.com","unit_testing_by_hari")
		self.assertEqual(result,"ok")

class flask_route_testcase(unittest.TestCase):
	def test_registerroute(self):
		tester = app.test_client(self)
		response = tester.get('/register')
		self.assertEqual(response.status_code,200)
	
	def test_loginroute(self):
		tester = app.test_client(self)
		response = tester.get('/login')
		self.assertEqual(response.status_code,200)
	
	def test_homeroute(self):
		tester = app.test_client(self)
		response = tester.get('/home')
		self.assertEqual(response.status_code,200)
	
	def test_addcertroute(self):
		tester = app.test_client(self)
		response = tester.get('/addcertificate')
		self.assertEqual(response.status_code,200)
	
	def test_usercertroute(self):
		tester = app.test_client(self)
		response = tester.get('/usercertificate')
		self.assertEqual(response.status_code,200)
	
	def test_quizdisproute(self):
		tester = app.test_client(self)
		response = tester.get('/quizdisplay')
		self.assertEqual(response.status_code,200)
	
	def test_contentroute(self):
		tester = app.test_client(self)
		response = tester.get('/content')
		self.assertEqual(response.status_code,200)
	
	def test_brainstormroute(self):
		tester = app.test_client(self)
		response = tester.get('/brainstroming')
		self.assertEqual(response.status_code,200)
	
	def test_uploadcertroute(self):
		tester = app.test_client(self)
		response = tester.get('/upload-cert')
		self.assertEqual(response.status_code,200)
	
	def test_studyboardroute(self):
		tester = app.test_client(self)
		response = tester.get('/studyboard')
		self.assertEqual(response.status_code,200)
	
	def test_signoutroute(self):
		tester = app.test_client(self)
		response = tester.get('/signout')
		self.assertEqual(response.status_code,200)
	
	def itest_takequizroute(self):
		tester = app.test_client(self)
		response = tester.get('/takequiz')
		self.assertEqual(response.status_code,200)


class Flask_respodata_testcase(unittest.TestCase):
	def test_responroute(self):
		tester = app.test_client(self)
		response = tester.get('/register')
		self.assertTrue(b'Sign Up' in response.data)
	
	def test_correctlogin(self):
		tester = app.test_client(self)
		response = tester.post('/login',data=dict(email="dharinath@virtusa.com",password="ok"), follow_redirects=True) 
		self.assertTrue(b'Home' in response.data)
	
	def test_incorrectlogin(self):
                tester = app.test_client(self)
                response = tester.post('/login',data=dict(email="dharinath@virtusa.com",password="ook"), follow_redirects=True)
                #self.assertTrue(b'Sign Up' in response.data)
                self.assertFalse(b'Home' in response.data)

if __name__ == '__main__':
	unittest.main()
